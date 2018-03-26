package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import pl.mwieczerzak.dailymealsapp.dto.NewMealDto;
import static pl.mwieczerzak.dailymealsapp.repository.MealRepository.meals;


@Service
public class MealService {

    public void deleteMeal(Long id) {
        meals.removeIf(m -> m.getId().equals(id));
    }

    public void addMeal(NewMealDto meal) {
        meals.add(MealDto.builder()
                .name(meal.getName())
                .mealDate(meal.getMealDate())
                .id(meal.getId() == null ? meals.size() + 1 : meal.getId())
                .proteins(meal.getProteins())
                .carbs(meal.getCarbs())
                .fats(meal.getFats())
                .calories(meal.getCalories())
                .build());
    }

}


