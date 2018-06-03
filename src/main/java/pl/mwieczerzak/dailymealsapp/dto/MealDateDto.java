package pl.mwieczerzak.dailymealsapp.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MealDateDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
