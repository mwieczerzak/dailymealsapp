package pl.mwieczerzak.dailymealsapp.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMealDto {

        private Long id;
        private String name;
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        private LocalDate mealDate;
        private BigDecimal proteins;
        private BigDecimal carbs;
        private BigDecimal fats;
        private BigDecimal calories;

    }