package club.pard.server.seminar03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.seminar03.entity.UserEntity;
import java.util.List;
// import java.sql.Timestamp;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
    // List<UserEntity> findTop3ByUserSignUpTimeOrderByUserSignUpTimeDesc();
}
