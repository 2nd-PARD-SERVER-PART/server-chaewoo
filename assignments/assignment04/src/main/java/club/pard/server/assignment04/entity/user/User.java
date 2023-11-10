package club.pard.server.assignment04.entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

import club.pard.server.assignment04.entity.task.Task;

@Entity
@NoArgsConstructor
public class User {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id = null;
    @Getter @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) private List<Task> tasks = new ArrayList<>();
    @Getter @Column(unique = true, nullable = false) private String email;
    @Getter @Column(nullable = false) private String name;
    @Column(nullable = false) private String password;

    public User(String email, String password, String name){ this.email = email; this.password = password; this.name = name; }

    // // Customized setters
    public void update(String password, String name){ this.password = password; this.name = name; }
}
