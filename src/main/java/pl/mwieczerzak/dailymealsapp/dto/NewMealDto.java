package pl.mwieczerzak.dailymealsapp.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mwieczerzak.dailymealsapp.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMealDto {

        private Long id;
        @NotEmpty
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
        private User user;

    }