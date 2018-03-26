package pl.mwieczerzak.dailymealsapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {

    @GetMapping(value = "/main")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView("main");
        return modelAndView;
    }
}
