package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.CriteriaDto;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import pl.mwieczerzak.dailymealsapp.entity.Meal;
import pl.mwieczerzak.dailymealsapp.repository.MealRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MealFinder {

    private final MealRepository mealRepository;

    @Autowired
    public MealFinder(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<MealDto> findMeals() {
        return mealRepository.findAll()
                .stream()
                .map(this::mapMeal)
                .collect(Collectors.toList());
    }

    private MealDto mapMeal(Meal meal) {
        return MealDto.builder()
                .name(meal.getName())
                .mealDate(meal.getMealDate())
                .id(meal.getId())
                .proteins(meal.getProteins())
                .carbs(meal.getCarbs())
                .fats(meal.getFats())
                .calories(meal.getCalories())
                .build();
    }

    public MealDto findMealsDetails(Long id) {
        return Optional.ofNullable(mealRepository.findOne(id))
                .map(this::mapMeal)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<MealDto> findByCriteria(CriteriaDto cr) {
        return mealRepository.findMealsByCaloriesBetween(cr.getFrom(), cr.getTo())
                .stream()
                .map(this::mapMeal)
                .collect(Collectors.toList());
    }
}
