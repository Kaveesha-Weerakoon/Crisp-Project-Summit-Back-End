package com.example.Crisp.controller;

import com.example.Crisp.dto.Productdto;
import com.example.Crisp.dto.Userdto;
import com.example.Crisp.model.User;
import com.example.Crisp.repo.UserRepo;
import com.example.Crisp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value="/api/v1/public/admin/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Page<Userdto>> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok().body(userService.getAllUsers(page, size));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Userdto> getUserById(@PathVariable(value = "id") String id) {
        System.out.println(id);
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Userdto> getUserByEmail(@PathVariable(value = "email") String id) {
        return ResponseEntity.ok().body(userService.getUserByEmail(id));
    }


}
