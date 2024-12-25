package com.waste.wastTracker.service;

import com.waste.wastTracker.dto.UserDTO;
import com.waste.wastTracker.model.User;
import com.waste.wastTracker.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(User user) {

        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        //encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.getLastLogin(LocalDate.now());

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDTO(user);
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO updateUser(Long id, User userDetails) {

        getUserById(id); // check if the user exist or will throw an error

        // verification for the password if it is empty and encodded the new value
        if(userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword())); // the parameter is passed by reference
        }

        User updatedUser = userRepository.save(userDetails);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO(
                user.getUsername(),
                user.getEmail(),
                user.getRole().toString(),
                user.getFirstName(),
                user.getLastName()
        );
        return userDTO;
    }

}
