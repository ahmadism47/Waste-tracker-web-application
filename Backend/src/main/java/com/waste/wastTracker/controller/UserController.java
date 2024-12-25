package com.waste.wastTracker.controller;

import com.waste.wastTracker.dto.UserDTO;
import com.waste.wastTracker.model.User;
import com.waste.wastTracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") //allows requests from any origin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user){
        UserDTO userDTO = userService.createUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user){
        UserDTO userDTO = userService.updateUser(user.getId(), user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){ // ?: unknown type
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username){
        UserDTO userDTO = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
