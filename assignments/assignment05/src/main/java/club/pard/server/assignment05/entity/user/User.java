package club.pard.server.assignment05.entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

import club.pard.server.assignment05.dto.request.user.UserSignUpRequest;
import club.pard.server.assignment05.entity.task.Task;

@Entity
@NoArgsConstructor
@Table
public class User {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id = null;
    @Getter @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) private List<Task> tasks = new ArrayList<>();
    @Getter @Column(unique = true, nullable = false) private String email;
    @Getter @Column(nullable = false) private String name;
    @Setter @Getter @Column(nullable = false) private String password;

    // public User(String email, String password, String name){ this.email = email; this.password = password; this.name = name; }
    public User(UserSignUpRequest request){ this.email = request.getEmail(); this.password = request.getPassword(); this.name = request.getName(); }

    // // Customized setters
    public void update(String password, String name){ this.password = password; this.name = name; }
}
