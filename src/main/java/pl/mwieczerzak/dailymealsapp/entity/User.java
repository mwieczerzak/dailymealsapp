package pl.mwieczerzak.dailymealsapp.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String password;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user")
    private Set<Meal> meals;
}
