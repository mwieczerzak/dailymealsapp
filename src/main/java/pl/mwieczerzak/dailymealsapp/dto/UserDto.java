package pl.mwieczerzak.dailymealsapp.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
@Data
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
