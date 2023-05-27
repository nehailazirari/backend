package com.example.backend.services;

import com.example.backend.autre.TaskStatistics;
import com.example.backend.autre.TaskStatut;
import com.example.backend.models.Task;
import com.example.backend.models.User;
import com.example.backend.repository.TaskRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;



    public Task createTask(User user, String title, String description, Date dueDate) {

        Task task = new Task();
        task.setId(sequenceGenerator.generateSequence(Task.SEQUENCE_NAME));
        task.setUser(user);
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatut(TaskStatut.IN_PROGRESS); // Définir le statut initial de la tâche comme "en cours"

        // Enregistrer la tâche dans la base de données
        return taskRepository.insert(task);
    }


    public List<Task> getAllTasksByUser(Long id) {

        Optional<User> user = userRepository.findById(id);
        // Récupérer toutes les tâches de l'utilisateur à partir du référentiel
        return taskRepository.findByUser(user);
    }

    public void updateTask(Optional<User> user, Long taskId, String title, String description, Date dueDate, TaskStatut status) {
        // Rechercher la tâche par ID et utilisateur

        Optional<Task> optionalTask = taskRepository.findByIdAndUser(taskId, user);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            // Mettre à jour les détails de la tâche
            task.setTitle(title);
            task.setDescription(description);
            task.setDueDate(dueDate);
            task.setStatut(status);

            // Enregistrer les modifications dans la base de données
            taskRepository.save(task);
        } else {
            // La tâche n'a pas été trouvée, vous pouvez lever une exception appropriée ou gérer l'absence de tâche.
        }
    }


    public void deleteTask(Task task) {
        // Supprimer la tâche de la base de données
        taskRepository.delete(task);
    }

    public void markTaskAsCompleted(Optional<User> user, Long taskId) {
        // Rechercher la tâche par ID et utilisateur
        Optional<Task> optionalTask = taskRepository.findByIdAndUser(taskId, user);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            // Marquer la tâche comme terminée
            task.setStatut(TaskStatut.COMPLETED);

            // Enregistrer la modification dans la base de données
            taskRepository.save(task);
        } else {
            // La tâche n'a pas été trouvée, vous pouvez lever une exception appropriée ou gérer l'absence de tâche.
        }
    }


    public List<Task> searchTasksByKeyword(User user, String keyword) {
        // Rechercher les tâches de l'utilisateur contenant le mot-clé dans le titre ou la description
        return taskRepository.findByUserAndTitleContainingOrUserAndDescriptionContaining(user, keyword, user, keyword);
    }

    public List<Task> searchTasksByCriteria(User user, TaskStatut status) {
        // Rechercher les tâches de l'utilisateur en fonction du statut
        return taskRepository.findByUserAndStatut(user, status);
    }

    public TaskStatistics getTaskStatistics(User user) {
        // Récupérer les statistiques des tâches de l'utilisateur à partir du référentiel
        int totalTasks = taskRepository.countByUser(user);
        int completedTasks = taskRepository.countByUserAndStatut(user, TaskStatut.COMPLETED);

        return new TaskStatistics(totalTasks, completedTasks);
    }
}
