package pl.mwieczerzak.dailymealsapp.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.dto.UserDto;
import pl.mwieczerzak.dailymealsapp.entity.User;
import pl.mwieczerzak.dailymealsapp.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFinder {

    private final UserRepository userRepository;

    @Autowired
    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapUser)
                .collect(Collectors.toList());
    }

    public UserDto findUserDetails(Long id) {
        return Optional.ofNullable(userRepository.findOne(id))
                .map(this::mapUser)
                .orElseThrow(NoSuchElementException::new);
    }

    private UserDto mapUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public User findLoggedUser() {
        return Optional.ofNullable(userRepository.findUserByLogin(findUserLogin()))
                .orElseThrow(NoSuchElementException::new);
    }

    private String findUserLogin() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        return auth.getName();
    }
}