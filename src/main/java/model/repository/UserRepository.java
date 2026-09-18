package model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.userhierarchy.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
