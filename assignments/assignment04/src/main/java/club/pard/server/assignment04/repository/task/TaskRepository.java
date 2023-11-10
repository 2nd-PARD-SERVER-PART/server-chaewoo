package club.pard.server.assignment04.repository.task;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.assignment04.entity.task.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    Optional<Task> findById(Long id);
    Optional<List<Task>> findAllByUserId(Long id);
}
