package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.CriteriaDto;
import pl.mwieczerzak.dailymealsapp.dto.MealDateDto;
import pl.mwieczerzak.dailymealsapp.dto.NewMealDto;
import pl.mwieczerzak.dailymealsapp.entity.Meal;
import pl.mwieczerzak.dailymealsapp.repository.MealRepository;

import java.math.BigDecimal;
import java.util.stream.Collectors;

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
                .calories(meal.getCarbs().multiply(new BigDecimal(4))
                        .add(meal.getProteins().multiply(new BigDecimal(4)))
                        .add(meal.getFats().multiply(new BigDecimal(9))))
                .build();
    }

    public BigDecimal sumAllCalories() {
        BigDecimal sum = new BigDecimal(0);
        for (Meal meal : mealRepository.findAll().stream().collect(Collectors.toList())) {
            sum = sum.add(meal.getCalories());
        }
        return sum;
    }

    public BigDecimal sumDailyCalories(MealDateDto date) {
        BigDecimal sum = new BigDecimal(0);
        for (Meal meal : mealRepository.findMealsByMealDateEquals(date.getDate())
                .stream().collect(Collectors.toList())) {
            sum = sum.add(meal.getCalories());
        }
        return sum;
    }

    public BigDecimal sumCriteriaCalories(CriteriaDto criteria) {
        BigDecimal sum = new BigDecimal(0);
        for (Meal meal : mealRepository.findMealsByCaloriesBetween(criteria.getFrom(), criteria.getTo())
                .stream().collect(Collectors.toList())) {
            sum = sum.add(meal.getCalories());
        }
        return sum;
    }

}