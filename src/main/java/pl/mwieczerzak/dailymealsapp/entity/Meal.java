package pl.mwieczerzak.dailymealsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private LocalDate mealDate;
    private BigDecimal proteins;
    private BigDecimal carbs;
    private BigDecimal fats;
    private BigDecimal calories;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Meal(String createdBy, String modifiedBy, Long id, String name, LocalDate mealDate,
                BigDecimal proteins, BigDecimal carbs, BigDecimal fats, BigDecimal calories, User user) {
        super(createdBy, modifiedBy);
        this.id = id;
        this.name = name;
        this.mealDate = mealDate;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
        this.calories = calories;
        this.user = user;
    }


}
