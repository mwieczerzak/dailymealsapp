package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.CriteriaDto;
import pl.mwieczerzak.dailymealsapp.dto.MealDateDto;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import pl.mwieczerzak.dailymealsapp.entity.Meal;
import pl.mwieczerzak.dailymealsapp.repository.MealRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MealFinder {

    private final MealRepository mealRepository;
    private final UserFinder userFinder;

    @Autowired
    public MealFinder(MealRepository mealRepository, UserFinder userFinder) {
        this.mealRepository = mealRepository;
        this.userFinder = userFinder;
    }

    public List<MealDto> findAllMeals() {
        return mealRepository.findAll()
                .stream()
                .map(this::mapMeal)
                .collect(Collectors.toList());
    }

    public MealDto findSingleMealDetails(Long id) {
        return Optional.ofNullable(mealRepository.findOne(id))
                .map(this::mapMeal)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<MealDto> findUserMealsByCalories(CriteriaDto criteria) {
        return mealRepository.findMealsByUserIdAndCaloriesBetween(userFinder.findLoggedUser().getId(),
                criteria.getFrom(), criteria.getTo())
                .stream()
                .map(this::mapMeal)
                .collect(Collectors.toList());
    }

    public List<MealDto> findUserMealsByDate(MealDateDto date) {
        return findMealsByUserId(userFinder.findLoggedUser().getId())
                .stream()
                .filter(meal -> meal.getMealDate().equals(date.getDate()))
                .collect(Collectors.toList());
    }

    public List<MealDto> findMealsByUserId(Long id) {
        return mealRepository.findMealsByUserId(id)
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

    public Set<LocalDate> findDatesByUserMeals() {
        return findMealsByUserId(userFinder.findLoggedUser().getId())
                .stream()
                .map(MealDto::getMealDate)
                .collect(Collectors.toSet());
    }
}