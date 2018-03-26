package pl.mwieczerzak.dailymealsapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mwieczerzak.dailymealsapp.bo.MealService;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import pl.mwieczerzak.dailymealsapp.dto.NewMealDto;

@Controller
public class MealsController {

    private final MealService service;

    @Autowired
    public MealsController(MealService service) {
        this.service = service;
    }


    @GetMapping(value = "/meals")
    //@RequestMapping(value = "/costs", method = RequestMethod.GET)
    public ModelAndView mealsPage() {
        ModelAndView mav = new ModelAndView("meals");
        mav.addObject("meals", service.findMeals());
        return mav;
    }

    @GetMapping(value = "/meal/{id}")
    public ModelAndView mealDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("mealDetails");
        mav.addObject("meal", service.findMealsDetails(id));
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

        MealDto cd = service.findMealsDetails(id);
        model.addAttribute("newMeal", NewMealDto.builder()
                .name(cd.getName())
                .mealDate(cd.getMealDate())
                .proteins(cd.getProteins())
                .carbs(cd.getCarbs())
                .fats(cd.getFats())
                .id(cd.getId())
                .build());


        return "edit";
    }

    @PostMapping(value = "meal/add")
    public String saveMeal(@ModelAttribute("newCost") NewMealDto form,
                           BindingResult result, Model model) {

        if (form.getId() != null) {
            service.deleteMeal(form.getId());
        }

        service.addMeal(form);

        return "redirect:../costs";
    }


}