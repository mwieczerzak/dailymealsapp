package pl.mwieczerzak.dailymealsapp.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleError(RuntimeException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("msg", ex.getStackTrace());
        return mav;
    }

}
