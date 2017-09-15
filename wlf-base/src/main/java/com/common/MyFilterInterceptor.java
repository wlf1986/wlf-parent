package com.common;

import com.user.controller.UserController;
import com.utils.BlankUtil;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/8/17.
 */
public class MyFilterInterceptor implements HandlerInterceptor {

    @Autowired
    private UserController userController;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        page(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void page(HttpServletRequest request) {
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        String isDesc = request.getParameter("isDesc");
        if (BlankUtil.isBlank(pageSize) || BlankUtil.isBlank(pageNum)) {
            PageUtil.setPage(new Page(false));
            return;
        }
        Page page = new Page(Integer.parseInt(pageSize), Integer.parseInt(pageNum), Boolean.valueOf(isDesc));
        PageUtil.setPage(page);
    }
}
