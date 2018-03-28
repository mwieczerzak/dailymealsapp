package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.CriteriaDto;
import pl.mwieczerzak.dailymealsapp.dto.MealDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import static pl.mwieczerzak.dailymealsapp.repository.MealRepository.meals;

@Service
public class MealFinder {

    public List<MealDto> findMeals() {
        return meals;
    }

    public MealDto findMealsDetails(Long id) {
        return meals.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst().orElseThrow(() -> new NoSuchElementException());
    }

    public List<MealDto> findByCriteria(CriteriaDto cr) {
        return meals.stream()
                .filter(c -> Objects.isNull(cr.getFrom()) || cr.getFrom().compareTo(c.getCalories()) < 0)
                .filter(c -> Objects.isNull(cr.getTo()) || cr.getTo().compareTo(c.getCalories()) > 0)
                .collect(Collectors.toList());
    }
}
