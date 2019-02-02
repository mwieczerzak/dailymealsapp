package pl.mwieczerzak.dailymealsapp.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import pl.mwieczerzak.dailymealsapp.entity.Meal;

import java.util.List;

@Builder
@Getter
@Data
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private List<Meal> meals;
}
