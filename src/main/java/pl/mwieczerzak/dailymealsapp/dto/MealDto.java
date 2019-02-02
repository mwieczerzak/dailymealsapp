package pl.mwieczerzak.dailymealsapp.dto;

import lombok.*;
import pl.mwieczerzak.dailymealsapp.entity.User;

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

    private User user;





}
