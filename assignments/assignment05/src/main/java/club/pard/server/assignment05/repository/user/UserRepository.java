package club.pard.server.assignment05.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.assignment05.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}