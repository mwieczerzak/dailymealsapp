package pl.mwieczerzak.dailymealsapp.dto;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMealDto {

        private Long id;
        @javax.validation.constraints.NotEmpty
        private String name;
        @PastOrPresent
        @NotNull
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        private LocalDate mealDate;
        @NotNull
        private BigDecimal proteins;
        @NotNull
        private BigDecimal carbs;
        @NotNull
        private BigDecimal fats;
        private BigDecimal calories;

    }