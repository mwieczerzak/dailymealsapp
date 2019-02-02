package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.CriteriaDto;
import pl.mwieczerzak.dailymealsapp.dto.MealDateDto;
import pl.mwieczerzak.dailymealsapp.dto.NewMealDto;
import pl.mwieczerzak.dailymealsapp.entity.Meal;
import pl.mwieczerzak.dailymealsapp.repository.MealRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final UserFinder userFinder;

    @Autowired
    public MealService(MealRepository mealRepository, UserFinder userFinder) {
        this.mealRepository = mealRepository;
        this.userFinder = userFinder;
    }

    public void deleteMeal(Long id) {
        mealRepository.delete(id);
    }

    public void addMeal(NewMealDto meal) {
        mealRepository.save(getMealFromDto(meal));
    }

    public void editMeal(NewMealDto meal) {
        String name = meal.getName();
        LocalDate mealDate = meal.getMealDate();
        BigDecimal proteins = meal.getProteins();
        BigDecimal carbs = meal.getCarbs();
        BigDecimal fats = meal.getFats();
        BigDecimal calories = calculateCalories(meal);
        Long id = meal.getId();
        mealRepository.updateMealById(name, mealDate, proteins, carbs, fats, calories, id);
    }

    private Meal getMealFromDto(NewMealDto meal) {
        return Meal.builder()
                .id(meal.getId())
                .name(meal.getName())
                .mealDate(meal.getMealDate())
                .proteins(meal.getProteins())
                .carbs(meal.getCarbs())
                .fats(meal.getFats())
                .calories(calculateCalories(meal))
                .user(userFinder.findLoggedUser())
                .build();
    }

    private BigDecimal calculateCalories(NewMealDto meal) {
        return Stream.of(
                meal.getProteins().multiply(new BigDecimal(4)),
                meal.getCarbs().multiply(new BigDecimal(4)),
                meal.getFats().multiply(new BigDecimal(9)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal sumAllCalories() {
        BigDecimal sum = new BigDecimal(0);
        for (Meal meal : mealRepository.findAll()) {
            sum = sum.add(meal.getCalories());
        }
        return sum;
    }

    public BigDecimal sumUserCalories(Long userId) {
        BigDecimal sum = new BigDecimal(0);
        for (Meal meal : mealRepository.findMealsByUserId(userId)) {
            sum = sum.add(meal.getCalories());
        }
        return sum;
    }

    public BigDecimal sumUserDailyCalories(MealDateDto date) {
        BigDecimal sum = new BigDecimal(0);
        for (Meal meal : mealRepository.findMealsByMealDateAndAndUserId(date.getDate(), userFinder.findLoggedUser().getId())) {
            sum = sum.add(meal.getCalories());
        }
        return sum;
    }

    public BigDecimal sumCriteriaCalories(CriteriaDto criteria) {
        BigDecimal sum = new BigDecimal(0);
        for (Meal meal : mealRepository.findMealsByUserIdAndCaloriesBetween(userFinder.findLoggedUser().getId(),
                criteria.getFrom(), criteria.getTo())) {
            sum = sum.add(meal.getCalories());
        }
        return sum;
    }
}