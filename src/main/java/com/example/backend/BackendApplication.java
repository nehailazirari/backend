package com.example.backend;

import com.example.backend.models.Task;
import com.example.backend.models.User;
import com.example.backend.repository.TaskRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.SequenceGeneratorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    /*@Bean
    CommandLineRunner runner(UserRepository urepo, TaskRepository taskRepository, SequenceGeneratorService sequenceGenerator){
        return args -> {
            User user = new User("H123K", "KAFs12");
            user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
            urepo.insert(user);

            Date today = new Date();


            Task task1 = new Task("Task 1", "Description 1", today, false, user);
            Task task2 = new Task("Task 2", "Description 2", today, false, user);
            Task task3 = new Task("Task 3", "Description 3", today, false, user);
            task1.setId(sequenceGenerator.generateSequence(Task.SEQUENCE_NAME));
            task2.setId(sequenceGenerator.generateSequence(Task.SEQUENCE_NAME));
            task3.setId(sequenceGenerator.generateSequence(Task.SEQUENCE_NAME));
            taskRepository.insert(task1);
            taskRepository.insert(task2);
            taskRepository.insert(task3);
        };
    }*/
}
