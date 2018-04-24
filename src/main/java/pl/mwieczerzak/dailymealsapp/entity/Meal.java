package pl.mwieczerzak.dailymealsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDate mealDate;
    private BigDecimal proteins;
    private BigDecimal carbs;
    private BigDecimal fats;
    private BigDecimal calories;
}
