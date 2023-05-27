package com.example.backend.repository;

import com.example.backend.autre.TaskStatut;
import com.example.backend.models.Task;
import com.example.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, Long> {
    List<Task> findByUser(Optional<User> user);

    Optional<Task> findByIdAndUser(Long taskId, Optional<User> user);

    List<Task> findByUserAndTitleContainingOrUserAndDescriptionContaining(User user, String keyword, User user1, String keyword1);

    List<Task> findByUserAndStatut(User user, TaskStatut status);

    int countByUser(User user);

    int countByUserAndStatut(User user, TaskStatut completed);
}
