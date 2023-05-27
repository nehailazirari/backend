package com.example.backend.controller;

import com.example.backend.models.User;
import com.example.backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;



    @PostMapping("/signup")
    public void signUp(@RequestBody User signUpRequest) throws Exception {
        // Validation des données d'inscription
        // ...

        // Création de l'utilisateur
        authService.registerUser(signUpRequest.getUsername(), signUpRequest.getPassword());

    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody User loginRequest) throws Exception {
        // Validation des données de connexion
        // ...

        // Authentification de l'utilisateur
        User user = authService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @PostMapping("/users/usercheck")
    public ResponseEntity<?> userCheck(@RequestBody Map<String, Object> inputData) {

        String username = (String)inputData.get("username");
        Optional<Object> user = authService.userExists(username);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
