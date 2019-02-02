package pl.mwieczerzak.dailymealsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.mwieczerzak.dailymealsapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.firstName = ?1, u.lastName = ?2, u.login = ?3, u.password = ?4 WHERE u.id = ?5")
    void updateUserById(String firstname, String lastname, String login, String password, Long id);
}
