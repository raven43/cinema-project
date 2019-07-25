package com.raven43.cinemaproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(
            HttpServletRequest request,
            Model model
    ) {
        String status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
        String message;
        switch (status) {
            case "400":
                message = "Bad Request";
                break;
            case "403":
                message = "Forbidden";
                break;
            case "404":
                message = "Not found";
                break;
            default:
                switch (status.charAt(0)) {
                    case '4':
                        message = "You are doing something wrong";
                        break;
                    case '5':
                        message = "Some server trouble";
                        break;
                    default:
                        message = "Something went wrong";
                }
                break;
        }
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
