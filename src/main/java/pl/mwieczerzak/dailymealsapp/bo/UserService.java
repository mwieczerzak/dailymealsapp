package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.NewUserDto;
import pl.mwieczerzak.dailymealsapp.entity.User;
import pl.mwieczerzak.dailymealsapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public void addUser(NewUserDto user) {
        userRepository.save(getUserFromDto(user));
    }

    public void editUser(NewUserDto user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String login = user.getLogin();
        String password = user.getPassword();
        Long id = user.getId();
        userRepository.updateUserById(firstName, lastName, login, password, id);
    }

    private User getUserFromDto(NewUserDto user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
