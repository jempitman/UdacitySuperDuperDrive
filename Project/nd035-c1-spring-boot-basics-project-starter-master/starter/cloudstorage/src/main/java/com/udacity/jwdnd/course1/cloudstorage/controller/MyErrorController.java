package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null){
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                //System.out.println("404 Error");
                model.addAttribute("error", "notFound");
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                //System.out.println("500 error");
                model.addAttribute("error", "general");
            } else if(statusCode == HttpStatus.FORBIDDEN.value()){
                model.addAttribute("error", "fileUploadSize");
            }
        }

        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
