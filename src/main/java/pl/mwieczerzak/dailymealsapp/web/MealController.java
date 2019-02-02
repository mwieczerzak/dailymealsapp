package pl.mwieczerzak.dailymealsapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mwieczerzak.dailymealsapp.bo.MealFinder;
import pl.mwieczerzak.dailymealsapp.bo.MealService;
import pl.mwieczerzak.dailymealsapp.bo.UserFinder;
import pl.mwieczerzak.dailymealsapp.dto.*;

import javax.validation.Valid;

@Controller
public class MealController {

    private final MealService service;
    private final MealFinder finder;
    private final UserFinder userFinder;

    @Autowired
    public MealController(MealService service, MealFinder finder, UserFinder userFinder) {
        this.service = service;
        this.finder = finder;
        this.userFinder = userFinder;
    }

    @GetMapping(value = "/meals")
    public ModelAndView mealsPage() {
        ModelAndView mav = new ModelAndView("meals");
        mav.addObject("meals", finder.findAllMeals());
        mav.addObject("sumCalories", service.sumAllCalories());
        return mav;
    }

    @GetMapping(value = "/users/{id}/meals")
    public ModelAndView mealsByUserId(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("mealsByUser");
        mav.addObject("findMealsByUserId", finder.findMealsByUserId(id));
        mav.addObject("sumCalories", service.sumUserCalories(id));
        return mav;
    }

    @GetMapping(value = "/users/meals")
    public ModelAndView loggedUserMeals() {
        ModelAndView mav = new ModelAndView("mealsByUser");
        mav.addObject("findMealsByUserId", finder.findMealsByUserId(userFinder.findLoggedUser().getId()));
        mav.addObject("sumCalories", service.sumUserCalories(userFinder.findLoggedUser().getId()));
        return mav;
    }

    @GetMapping(value = "/meals/{id}")
    public ModelAndView mealDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("mealDetails");
        mav.addObject("meal", finder.findSingleMealDetails(id));
        return mav;
    }

    @GetMapping(value = "/meals/delete/{id}")
    public String deleteMeal(@PathVariable("id") Long id) {
        service.deleteMeal(id);
        return "redirect:/users/" + userFinder.findLoggedUser().getId() + "/meals";
    }

    @GetMapping(value = "/meals/add")
    public String addMeal(Model model) {
        model.addAttribute("newMeal", new NewMealDto());
        return "edit";
    }

    @PostMapping(value = "/meals/add")
    public String saveMeal(@ModelAttribute("newMeal") @Valid NewMealDto form, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        } else {
            if (form.getId() != null) {
                service.editMeal(form);
            } else {
                service.addMeal(form);
            }
            return "redirect:/users/" + userFinder.findLoggedUser().getId() + "/meals";
        }
    }

    @GetMapping(value = "/meals/edit")
    public String editMeal(Model model, @RequestParam("id") Long id) {
        MealDto cd = finder.findSingleMealDetails(id);
        model.addAttribute("newMeal", NewMealDto.builder()
                .name(cd.getName())
                .mealDate(cd.getMealDate())
                .proteins(cd.getProteins())
                .carbs(cd.getCarbs())
                .fats(cd.getFats())
                .calories(cd.getCalories())
                .id(cd.getId())
                .user(cd.getUser())
                .build());
        return "edit";
    }

    @GetMapping(value = "meals/search")
    public String searchMealsByCalories(Model model) {
        model.addAttribute("criterias", new CriteriaDto());
        return "searchByCalories";
    }

    @PostMapping(value = "meals/search")
    public ModelAndView searchMealsByCalories(@ModelAttribute("criterias") @Valid CriteriaDto criteria,
                                              BindingResult result) {
        ModelAndView mav = new ModelAndView("searchByCalories");
        if (result.hasErrors()) {
            return mav;
        } else {
            mav = new ModelAndView("meals");
            mav.addObject("sumCalories", service.sumCriteriaCalories(criteria));
            mav.addObject("meals", finder.findUserMealsByCalories(criteria));
            return mav;
        }
    }

    @GetMapping(value = "meals/search/date")
    public String searchMealsByDate(Model model) {
        model.addAttribute("mealDates", new MealDateDto());
        model.addAttribute("dates", finder.findDatesByUserMeals());
        return "searchByDate";
    }

    @PostMapping(value = "meals/search/date")
    public ModelAndView searchMealsByDate(@ModelAttribute("mealDates") MealDateDto date) {
        ModelAndView mav = new ModelAndView("meals");
        mav.addObject("meals", finder.findUserMealsByDate(date));
        mav.addObject("sumCalories", service.sumUserDailyCalories(date));
        return mav;
    }
}