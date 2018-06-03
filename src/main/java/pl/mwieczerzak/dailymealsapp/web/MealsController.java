package pl.mwieczerzak.dailymealsapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mwieczerzak.dailymealsapp.bo.MealFinder;
import pl.mwieczerzak.dailymealsapp.bo.MealService;
import pl.mwieczerzak.dailymealsapp.dto.CriteriaDto;
import pl.mwieczerzak.dailymealsapp.dto.MealDateDto;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import pl.mwieczerzak.dailymealsapp.dto.NewMealDto;

import javax.validation.Valid;


@Controller
public class MealsController {

    private final MealService service;
    private final MealFinder finder;

    @Autowired
    public MealsController(MealService service, MealFinder finder) {
        this.service = service;
        this.finder = finder;
    }

    @GetMapping(value = "/meals")
    public ModelAndView mealsPage() {
        ModelAndView mav = new ModelAndView("meals");
        mav.addObject("meals", finder.findMeals());
        mav.addObject("sumCalories", service.sumAllCalories());
        return mav;
    }

    @GetMapping(value = "/meals/{id}")
    public ModelAndView mealDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("mealDetails");
        mav.addObject("meal", finder.findMealsDetails(id));
        return mav;
    }

    @PostMapping(value = "/meals/delete")
    public String deleteMeal(@RequestParam(name = "mealId") String id) {
        service.deleteMeal(Long.valueOf(id));
        return "redirect:../meals";
    }

    @GetMapping(value = "/meals/add")
    public String addMeal(Model model) {
        model.addAttribute("newMeal", new NewMealDto());
        return "edit";
    }

    @GetMapping(value = "/meals/edit")
    public String editMeal(Model model, @RequestParam("id") Long id) {
        MealDto cd = finder.findMealsDetails(id);
        model.addAttribute("newMeal", NewMealDto.builder()
                .name(cd.getName())
                .mealDate(cd.getMealDate())
                .proteins(cd.getProteins())
                .carbs(cd.getCarbs())
                .fats(cd.getFats())
                .calories(cd.getCalories())
                .id(cd.getId())
                .build());

        return "edit";
    }

    @PostMapping(value = "/meals/add")
    public String saveMeal(@ModelAttribute("newMeal") @Valid NewMealDto form,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        } else {
            if (form.getId() != null) {
                service.deleteMeal(form.getId());
            }
            service.addMeal(form);
            return "redirect:../meals";
        }
    }

    @GetMapping(value = "meals/search")
    public String search(Model model) {
        model.addAttribute("criterias", new CriteriaDto());
        return "search";
    }

    @PostMapping(value = "meals/search")
    public ModelAndView searchMeal(@ModelAttribute("criterias") @Valid CriteriaDto criteria,
                                   BindingResult result) {
        ModelAndView mav = new ModelAndView("search");
        if (result.hasErrors()) {
            return mav;
        } else {
            mav = new ModelAndView("meals");
            mav.addObject("sumCalories", service.sumCriteriaCalories(criteria));
            mav.addObject("meals", finder.findByCalories(criteria));
            return mav;
        }
    }

    @GetMapping(value = "meals/search/date")
    public String searchByDate(Model model) {
        model.addAttribute("mealDates", new MealDateDto());
        model.addAttribute("dates", finder.findAllDates());
        return "mealsByDate";
    }

    @PostMapping(value = "meals/search/date")
    public ModelAndView searchMealsByDate(@ModelAttribute("mealDates") MealDateDto date) {
        ModelAndView mav = new ModelAndView("meals");
        mav.addObject("meals", finder.findByDate(date));
        mav.addObject("sumCalories", service.sumDailyCalories(date));
        return mav;

    }


}


