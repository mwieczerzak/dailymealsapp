package pl.mwieczerzak.dailymealsapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CriteriaDto {

    @NotNull
    private BigDecimal from;
    @NotNull
    private BigDecimal to;

}
