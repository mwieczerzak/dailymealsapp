package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import pl.mwieczerzak.dailymealsapp.dto.NewMealDto;
import pl.mwieczerzak.dailymealsapp.entity.Meal;
import pl.mwieczerzak.dailymealsapp.repository.MealRepository;

@Service
public class MealService {

    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public void deleteMeal(Long id) {
        mealRepository.delete(id);
    }

    public void addMeal(NewMealDto meal) {
        mealRepository.save(getMealFromDto(meal));
    }

    private Meal getMealFromDto(NewMealDto meal) {
        return Meal.builder()
                .name(meal.getName())
                .mealDate(meal.getMealDate())
                .id(meal.getId())
                .proteins(meal.getProteins())
                .carbs(meal.getCarbs())
                .fats(meal.getFats())
                .calories(meal.getCalories())
                .build();
    }

}


