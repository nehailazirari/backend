package com.example.backend.controller;

import com.example.backend.models.Task;
import com.example.backend.models.User;
import com.example.backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private  TaskService taskService;


    @PostMapping("/createTask")
    public void createTask(@RequestBody Task createTaskRequest) {
        // Vérification de l'utilisateur authentifié


        // Création de la tâche
        Task task = taskService.createTask(createTaskRequest.getUser(), createTaskRequest.getTitle(), createTaskRequest.getDescription(), createTaskRequest.getDueDate());


    }

    @GetMapping("{idUser}/ALLTasks")
    public List<Task> getAllTasks(@PathVariable("idUser") Long id) {


        // Récupération de toutes les tâches de l'utilisateur
        return taskService.getAllTasksByUser(id);


    }



    @PutMapping("{idUser}/{taskId}")
    public void updateTask(@PathVariable("taskId") Long taskId, @RequestBody Task updateTaskRequest,@PathVariable("idUser") Optional<User> idUser){
        // Vérification de l'utilisateur authentifié


        // Mise à jour de la tâche
         taskService.updateTask(idUser, taskId, updateTaskRequest.getTitle(), updateTaskRequest.getDescription(), updateTaskRequest.getDueDate(), updateTaskRequest.getStatut());

    }
}
