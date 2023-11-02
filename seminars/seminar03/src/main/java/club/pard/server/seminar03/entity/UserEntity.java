package club.pard.server.seminar03.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;

import club.pard.server.seminar03.dto.SignUpDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int userNum;
    // Changes camelcase name to snakecase one(MariaDB/MySQL uses snakecase data names)
    @Column(columnDefinition = "TEXT", nullable = false) private String userEmail;
    @Column(length = 20) private String userPassword;

    @CreationTimestamp
    private Timestamp userSignUpTime;
    // @UpdateTimestamp

    public UserEntity(SignUpDto dto)
    {
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
    }
}
