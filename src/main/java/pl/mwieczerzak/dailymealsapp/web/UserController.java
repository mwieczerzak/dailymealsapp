package pl.mwieczerzak.dailymealsapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mwieczerzak.dailymealsapp.bo.UserFinder;
import pl.mwieczerzak.dailymealsapp.bo.UserService;
import pl.mwieczerzak.dailymealsapp.dto.NewUserDto;
import pl.mwieczerzak.dailymealsapp.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserFinder finder;
    private final UserService service;

    @Autowired
    public UserController(UserService service, UserFinder finder) {
        this.finder = finder;
        this.service = service;
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/main";
    }

    @GetMapping(value = "/users")
    public ModelAndView usersPage() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", finder.findUsers());
        return mav;
    }

    @GetMapping(value = "/users/user")
    public ModelAndView userDetails() {
        ModelAndView mav = new ModelAndView("usersDetails");
        mav.addObject("user", finder.findUserDetails(finder.findLoggedUser().getId()));
        return mav;
    }

    @GetMapping(value = "/users/add")
    public String addUser(Model model) {
        model.addAttribute("newUser", new NewUserDto());
        return "registration";
    }

    @GetMapping(value = "/users/edit")
    public String editUser(Model model, @RequestParam("id") Long id) {
        UserDto userDto = finder.findUserDetails(id);
        model.addAttribute("newUser", NewUserDto.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build());
        return "registration";
    }

    @PostMapping(value = "/users/add")
    public String saveUser(@ModelAttribute("newUser") @Valid NewUserDto form, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        } else {
            if (form.getId() != null) {
                service.editUser(form);
            } else {
                service.addUser(form);
            }
            return "redirect:../main";
        }
    }

    @PostMapping(value = "/users/delete")
    public String deleteUser(@RequestParam("userId") String id) {
        service.deleteUser(Long.valueOf(id));
        return "redirect:../users";
    }
}