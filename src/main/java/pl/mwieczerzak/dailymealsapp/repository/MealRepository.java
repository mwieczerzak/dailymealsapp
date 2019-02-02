package pl.mwieczerzak.dailymealsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.mwieczerzak.dailymealsapp.entity.Meal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findMealsByUserIdAndCaloriesBetween(Long id, BigDecimal from, BigDecimal to);

    List<Meal> findMealsByUserId(Long id);

    List<Meal> findMealsByMealDateAndAndUserId(LocalDate date, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Meal m SET m.name = ?1, m.mealDate = ?2, m.proteins = ?3," +
            " m.carbs = ?4, m.fats = ?5, m.calories = ?6 WHERE m.id = ?7")
    void updateMealById(String name, LocalDate date, BigDecimal proteins,
                        BigDecimal carbs, BigDecimal fats, BigDecimal calories, Long id);
}
