package com.christmas.controller;

import com.christmas.domain.Input;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InputController {

    ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/api/menu")
    public ResponseEntity<String> receiveOrderAndDate(@RequestBody String messageBody) throws JsonProcessingException {
        
        Input input = objectMapper.readValue(messageBody, Input.class);
        System.out.println(input.getDate());
        System.out.println(input.getOrder());
        return new ResponseEntity<>("Order received successfully", HttpStatus.OK);
    }
}
