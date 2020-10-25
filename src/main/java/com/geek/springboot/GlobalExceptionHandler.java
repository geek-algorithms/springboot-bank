
package com.geek.springboot;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
//@ControllerAdvice定义统一的异常处理
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";
//@ExceptionHandler用来定义函数针对的异常类型
    @ExceptionHandler(value=CanNotFreezeException.class)
    public ModelAndView adminErrorHandler(HttpServletRequest req,Exception e)throws Exception{
        ModelAndView mav = new ModelAndView("adminError");
        mav.addObject("exception", e);
        return mav;
    }

    @ExceptionHandler(value=CanNotRefreshException.class)
    public ModelAndView adminError(HttpServletRequest req,Exception e)throws Exception{
        ModelAndView mav = new ModelAndView("adminError");
        mav.addObject("exception", e);
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e ) throws Exception {
        ModelAndView mav = new ModelAndView();
        String es=e.getClass().getName();//异常类
        mav.addObject("exceptclass", es);
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
