package pl.mwieczerzak.dailymealsapp.dto;

import lombok.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

    private Long id;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
