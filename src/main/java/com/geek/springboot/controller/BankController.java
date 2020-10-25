package com.geek.springboot.controller;

import com.geek.springboot.AccountException;
import com.geek.springboot.NotTransferToSelfException;
import com.geek.springboot.pojo.LogBean;
import com.geek.springboot.pojo.PageBean;
import com.geek.springboot.pojo.UserBean;
import com.geek.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BankController {
    @Autowired
    private UserService service;

    @RequestMapping("login")
    public ModelAndView login(UserBean user, HttpServletRequest request)
            throws Exception {

        String msg = "";
        boolean flag = false;

        int identity = user.getIdentity();

        try {
            user = service.login(user);
            flag = user != null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 权限

        if (!flag) {
            msg = "老哥，账号或密码错误！";
            request.setAttribute("msg", msg);
            System.out.println("登录失败");
            return new ModelAndView(new RedirectView("login.html"));

        } else if (identity == 1) {
            request.getSession().setAttribute("user", user);
            System.out.println(user.getMoney());
            return new ModelAndView("userLeft");

        } else if (identity == 2) {
            request.getSession().setAttribute("user", user);
            return new ModelAndView("adminLeft");
        }

        return new ModelAndView(new RedirectView("login.html"));
    }

    /**
     * 注册
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/register")
    public ModelAndView register(UserBean user, HttpServletRequest request)
            throws Exception {
        String msg = "";
        boolean flag = false;
        try {
            flag = service.register(user);
        } catch (Exception e) {
            e.getStackTrace();
        }
        System.out.println("开始注册");
        if (!flag) {
            msg = "老哥，用户已存在，想用此id，除非把那人干掉！";
            request.setAttribute("msg", msg);
            System.out.println("注册失败");
            return new ModelAndView(new RedirectView("register.html"));
        } else {
            request.getSession().setAttribute("username", user.getUsername());
            System.out.println("注册成功");
            return new ModelAndView(new RedirectView("login.html"));
        }
    }

    /**
     * 退出
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/quit")
    public ModelAndView quit(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();//销毁session
        return new ModelAndView(new RedirectView("login.html"));
    }

    /**
     * 冻结违规用户
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/freeze")
    public ModelAndView freeze(String username, HttpServletRequest request)
            throws Exception {

        service.freeze(username);
        request.setAttribute("message", "用户："+username+"已被冻结");
        return new ModelAndView("show");
    }

    /**
     * 解除违规用户限制
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/refresh")
    public ModelAndView refresh(String username, HttpServletRequest request)
            throws Exception {

        service.refresh(username);

        request.setAttribute("message", "用户："+username+"已被激活");
        return new ModelAndView("show");
    }

    /**
     * 存钱
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/deposit")
    public ModelAndView deposit(double money, HttpServletRequest request)
            throws Exception {
        UserBean user = (UserBean) request.getSession().getAttribute("user");

        System.out.println(user.getMoney());
        try {
            service.deposit(user, money);
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("message", "存款成功");

        addSave(request);

        return new ModelAndView("findMoney");
    }

    /**
     * 取钱
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/withdraws")
    public ModelAndView withdraws(double money, HttpServletRequest request)
            throws Exception {
        UserBean user = (UserBean) request.getSession().getAttribute("user");

        if (user.getMoney() < money)
            throw new AccountException("余额不足");

        try {
            service.withdraws(user, money);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("message", "取款成功");

        addSave(request);

        return new ModelAndView("findMoney");
    }

    /**
     * 转账
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/transfer")
    public ModelAndView transfer(String name, double money, HttpServletRequest request)
            throws Exception {
        UserBean user = (UserBean) request.getSession().getAttribute("user");

        if (name.equals(user.getUsername()))
            throw new NotTransferToSelfException("不能转账给自己");
        if (money > user.getMoney())
            throw new AccountException("帐户余额不足");

        try {
            service.transfer(user, name, money);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("message", "转账成功!");

        addSave(request);

        return new ModelAndView("findMoney");
    }

    /**
     * 查询明细
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/log")
    public ModelAndView log(String username, HttpServletRequest request)
            throws Exception {
        UserBean user = (UserBean) request.getSession().getAttribute("user");

        try {
            List<LogBean> logList = service.findOperation(user);
            System.out.println(logList.size());
            request.setAttribute("logList",logList);
        }catch (Exception e){
            e.printStackTrace();
        }
//        PageBean pageBean = new PageBean(logList.size(), 8);
//        String tempPage = request.getParameter("tempPage");

//        request.setAttribute("tempPage", tempPage);
//        request.getSession().setAttribute("pageBean", pageBean);

        return new ModelAndView("log");
    }

    /**
     * 更新session中user的数据，同时添加日志
     * @param request
     * @return
     */
    public void addSave(HttpServletRequest request) {
        UserBean user= (UserBean) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", service.login(user));
    }
}
