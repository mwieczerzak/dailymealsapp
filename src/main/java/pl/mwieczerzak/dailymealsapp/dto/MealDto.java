package pl.mwieczerzak.dailymealsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealDto {

    private Long id;
    private String name;
    private LocalDate mealDate;
    private BigDecimal proteins;
    private BigDecimal carbs;
    private BigDecimal fats;
    private BigDecimal calories;



}
