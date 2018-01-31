package io.gearstack.controllers.web;

import io.gearstack.props.ServiceProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeController
 *
 * Web controller for home page requests
 */
@Controller
public class HomeController {

    @Autowired
    private ServiceProps serviceProps;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }
}
