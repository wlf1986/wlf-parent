package com.common;


import com.utils.JsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/8/17.
 */

@Component
public class MyException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        PrintWriter out =null;
        try {
            out = response.getWriter();
            out.println(JsonUtil.error("error!"));
        } catch (IOException e) {
        }finally {
            out.close();
        }
        return null;
    }
}
