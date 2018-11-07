package com.vandt.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoErrorController implements ErrorController
{
    @Override
    public String getErrorPath()
    {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError()
    {
        return "error";
    }
}
