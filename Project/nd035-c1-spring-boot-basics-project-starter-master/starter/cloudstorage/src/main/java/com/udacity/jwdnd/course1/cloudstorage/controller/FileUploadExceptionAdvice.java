package com.udacity.jwdnd.course1.cloudstorage.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FileUploadExceptionAdvice implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        if (e instanceof MaxUploadSizeExceededException){
            ModelAndView modelAndView = new ModelAndView("error.html");
            modelAndView.addObject("error", "fileSizeError");
            return modelAndView;

        }


        return new ModelAndView("home.html");
    }
}


