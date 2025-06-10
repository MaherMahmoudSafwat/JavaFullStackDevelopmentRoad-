package com.Example.demo3;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        //System.out.println("Home method called");
        return "index.jsp";
    }

    @RequestMapping("add")
    public String add(@RequestParam("num1") int X, @RequestParam("num2") int Y, HttpSession session)
    {

        int result=X+Y;
        session.setAttribute("result", result);

        return "results.jsp";
    }

}