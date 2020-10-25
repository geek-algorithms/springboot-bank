package com.geek.springboot.service;

import com.geek.springboot.CanNotFreezeException;
import com.geek.springboot.CanNotRefreshException;
import com.geek.springboot.NotTransferToSelfException;
import com.geek.springboot.UserNotFoundException;
import com.geek.springboot.dao.BankDaoMapper;
import com.geek.springboot.pojo.LogBean;
import com.geek.springboot.pojo.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private BankDaoMapper bankDaoMapper;

    public boolean findUser(String username) {
        if (bankDaoMapper.findUser(username) == null)
            return false;
        else
            return true;
    }

    public UserBean login(UserBean userBean) {
        return bankDaoMapper.login(userBean);
    }

    public boolean register(UserBean userBean) {
        if (findUser(userBean.getUsername()))
            return false;

        bankDaoMapper.insertUser(userBean);
        return true;
    }

    public void deposit(UserBean userBean, double money) {
        bankDaoMapper.deposit(userBean, money);
        addLog(new LogBean("deposit",money,new Date(),userBean));
    }

    public void withdraws(UserBean userBean, double money) {
        bankDaoMapper.withdraws(userBean, money);
        addLog(new LogBean("withdraws",money,new Date(),userBean));
    }

    public boolean transfer(UserBean user, String user_in, double money) {

        if (user.getUsername().equals(user_in))
            throw new NotTransferToSelfException("不能转账给自己");

        if (!findUser(user_in))
            throw new UserNotFoundException("目标帐户未找到");

        bankDaoMapper.transferIn(user_in, money);//目标帐户转入
        bankDaoMapper.transferOut(user.getUsername(), money);//当前帐户转出

        addLog(new LogBean("transfer to "+user_in,money,new Date(),user));
        return true;
    }

    public void addLog(LogBean log) {
        bankDaoMapper.addLog(log);
    }

    public List<LogBean> findOperation(UserBean user) {
        return bankDaoMapper.findOpration(user);
    }

    public void freeze(String username) {
        UserBean user=bankDaoMapper.findUser(username);

        if(user==null)
            throw new UserNotFoundException("目标帐户未找到");
        if(user.getIdentity()!=1)
            throw new CanNotFreezeException("帐户不能被冻结");

        bankDaoMapper.freezeAccount(username);
    }

    public void refresh(String username) {
        UserBean user=bankDaoMapper.findUser(username);

        if(user==null)
            throw new UserNotFoundException("目标帐户未找到");

        if(user.getIdentity()!=0)
            throw new CanNotRefreshException("帐户不能被解冻");

        bankDaoMapper.refreshAccount(username);
    }

}
