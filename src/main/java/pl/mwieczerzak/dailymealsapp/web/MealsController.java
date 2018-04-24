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
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import pl.mwieczerzak.dailymealsapp.dto.NewMealDto;
import pl.mwieczerzak.dailymealsapp.repository.MealRepository;

@Controller
public class MealsController {

    private final MealService service;
    private final MealFinder finder;

    @Autowired
    public MealsController(MealService service, MealFinder finder, MealRepository repository) {
        this.service = service;
        this.finder = finder;
    }

    @GetMapping(value = "/meals")
    public ModelAndView mealsPage() {
        ModelAndView mav = new ModelAndView("meals");
        mav.addObject("meals", finder.findMeals());
        mav.addObject("criteria", new CriteriaDto());
        return mav;
    }

    @GetMapping(value = "/meal/{id}")
    public ModelAndView mealDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("mealDetails");
        mav.addObject("meal", finder.findMealsDetails(id));
        return mav;
    }

    @PostMapping(value = "meal/delete")
    public String deleteMeal(@RequestParam(name = "mealId") String id) {
        service.deleteMeal(Long.valueOf(id));
        return "redirect:../meals";
    }

    @GetMapping(value = "meal/add")
    public String addMeal(Model model) {
        model.addAttribute("newMeal", new NewMealDto());
        return "edit";
    }

    @GetMapping(value = "meal/edit")
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

    @PostMapping(value = "meal/add")
    public String saveMeal(@ModelAttribute("newMeal") NewMealDto form,
                           BindingResult result, Model model) {

        if (form.getId() != null) {
            service.deleteMeal(form.getId());
        }

        service.addMeal(form);

        return "redirect:../meals";
    }

    @PostMapping(value = "byCalories")
    public ModelAndView search(@ModelAttribute("criteria") CriteriaDto criteria, BindingResult result, Model model) {
        ModelAndView mav = new ModelAndView("meals");
        mav.addObject("meals", finder.findByCriteria(criteria));
        mav.addObject("criteria", new CriteriaDto());
        return mav;

    }

}


