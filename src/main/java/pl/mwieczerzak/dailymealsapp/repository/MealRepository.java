package pl.mwieczerzak.dailymealsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mwieczerzak.dailymealsapp.entity.Meal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findMealsByCaloriesBetween(BigDecimal from, BigDecimal to);

    List<Meal> findMealsByMealDateEquals(LocalDate date);

}
