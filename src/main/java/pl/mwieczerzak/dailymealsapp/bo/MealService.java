package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import pl.mwieczerzak.dailymealsapp.dto.NewMealDto;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class MealService {


        private List<MealDto> meals = new ArrayList<>();

        @PostConstruct
        public void initList() {
            meals.add(MealDto.builder()
                    .id(1L)
                    .name("4 beers")
                    .mealDate(LocalDate.now())
                    .proteins(BigDecimal.valueOf(0).setScale(1))
                    .carbs(BigDecimal.valueOf(200).setScale(1))
                    .fats(BigDecimal.valueOf(10).setScale(1))
                    .calories(BigDecimal.valueOf(200*4 + 10*9).setScale(1))
                    .build());
            meals.add(MealDto.builder()
                    .id(2L)
                    .name("pizza")
                    .mealDate(LocalDate.now())
                    .proteins(BigDecimal.valueOf(30).setScale(1))
                    .carbs(BigDecimal.valueOf(180).setScale(1))
                    .fats(BigDecimal.valueOf(50).setScale(1))
                    .calories(BigDecimal.valueOf(30*4 + 180*4 + 50*9).setScale(1))
                    .build());
            meals.add(MealDto.builder()
                    .id(3L)
                    .name("5 eggs with bread")
                    .mealDate(LocalDate.now())
                    .proteins(BigDecimal.valueOf(35).setScale(1))
                    .carbs(BigDecimal.valueOf(70).setScale(1))
                    .fats(BigDecimal.valueOf(25).setScale(1))
                    .calories(BigDecimal.valueOf(35*4 + 70*4 + 25*9).setScale(1))
                    .build());
            meals.add(MealDto.builder()
                    .id(4L)
                    .name("pierogi ruskie")
                    .mealDate(LocalDate.now())
                    .proteins(BigDecimal.valueOf(25).setScale(1))
                    .carbs(BigDecimal.valueOf(130).setScale(1))
                    .fats(BigDecimal.valueOf(20).setScale(1))
                    .calories(BigDecimal.valueOf(25*4 + 130*4 + 20*9).setScale(1))
                    .build());
            meals.add(MealDto.builder()
                    .id(5L)
                    .name("dinner in restaurant")
                    .mealDate(LocalDate.now())
                    .proteins(BigDecimal.valueOf(30).setScale(1))
                    .carbs(BigDecimal.valueOf(75).setScale(1))
                    .fats(BigDecimal.valueOf(15).setScale(1))
                    .calories(BigDecimal.valueOf(30*4 + 75*4 + 15*9).setScale(1))
                    .build());
        }

        public List<MealDto> findMeals() {
            return this.meals;
        }


        public MealDto findMealsDetails(Long id) {
            return this.meals.stream().filter(c -> c.getId().equals(id))
                    .findFirst().orElseThrow(() -> new NoSuchElementException());
        }


        public void deleteMeal(Long id) {
            this.meals.removeIf(m -> m.getId().equals(id));
        }


        public void addMeal (NewMealDto meal) {
            this.meals.add(MealDto.builder()
                    .name(meal.getName())
                    .mealDate(meal.getMealDate())
                    .id(meal.getId() == null ? this.meals.size() + 1 : meal.getId())
                    .proteins(meal.getProteins())
                    .carbs(meal.getCarbs())
                    .fats(meal.getFats())
                    .calories(meal.getCalories())
                    .build());
        }

    }


