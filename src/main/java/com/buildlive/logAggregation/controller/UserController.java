package com.buildlive.logAggregation.controller;


import com.buildlive.logAggregation.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class UserController {

    @GetMapping("users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id){

        Long userId;

        try {
            userId = Long.parseLong(id);
        }
        catch (NumberFormatException e){
            String message = String.format("Invalid user id: %s. Please enter a valid numeric id",id);
            log.error(message );
            return ResponseEntity.badRequest().body(message);
        }

        log.info("Request with id = [{}]",userId);
        final User user = new User(userId,"name"+userId, LocalDateTime.now());
        log.info("Data retrieved id=[{}]",userId);
        return ResponseEntity.ok(user);
    }
}
