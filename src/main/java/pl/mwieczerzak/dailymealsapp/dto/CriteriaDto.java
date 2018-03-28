package pl.mwieczerzak.dailymealsapp.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class CriteriaDto {

    private BigDecimal from;
    private BigDecimal to;

}
