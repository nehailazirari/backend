package com.example.backend.services;

import com.example.backend.models.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;


    public User registerUser(String username, String password) throws Exception {
        // Vérifier si un utilisateur avec le même nom d'utilisateur existe déjà
        if (userRepository.existsByUsername(username)) {
            throw new Exception("Username already exists: " + username);
        }

        // Créer un nouvel utilisateur
        User user = new User();
        user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
        user.setUsername(username);
        user.setPassword(password); // Stocker le mot de passe en texte brut (à des fins de démonstration uniquement)
        // Vous pouvez également ajouter d'autres propriétés à l'utilisateur selon vos besoins

        // Enregistrer l'utilisateur dans la base de données
        return userRepository.insert(user);
    }

    public User loginUser(String username, String password) throws Exception {
        // Récupérer l'utilisateur par son nom d'utilisateur
        User user = (User) userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found: " + username));

        // Vérifier si le mot de passe correspond
        if (!user.getPassword().equals(password)) {
            throw new Exception("Invalid password");
        }

        return user;
    }

    public Optional<Object> userExists(String username) {

        Optional<Object> user = userRepository.findByUsername(username);

        return user;

    }
}
