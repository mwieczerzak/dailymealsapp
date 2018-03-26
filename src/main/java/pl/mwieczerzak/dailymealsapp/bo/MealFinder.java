package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;
import java.util.List;
import java.util.NoSuchElementException;
import static pl.mwieczerzak.dailymealsapp.repository.MealRepository.meals;

@Service
public class MealFinder {

    public List<MealDto> findMeals() {
        return meals;
    }

    public MealDto findMealsDetails(Long id) {
        return meals.stream().filter(c -> c.getId().equals(id))
                .findFirst().orElseThrow(() -> new NoSuchElementException());
    }
}
