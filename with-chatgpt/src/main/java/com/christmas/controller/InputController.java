package com.christmas.controller;

import com.christmas.domain.Input;
import com.christmas.domain.Receipt;
import com.christmas.service.PlannerService;
import com.christmas.view.InputView;
import com.christmas.view.OutputView;
import com.christmas.view.View;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
public class InputController {

    ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/api/menu")
    public ResponseEntity<String> receiveOrderAndDate(@RequestBody String messageBody) throws JsonProcessingException {

        Input input = objectMapper.readValue(messageBody, Input.class);
        Receipt result;
        String jsonString;
        try {
            result = makeReceipt(input);
            jsonString = objectMapper.writeValueAsString(result);
            System.out.println(jsonString);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("잘못된 입력", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    private Receipt makeReceipt(Input input) {
        View view = new View(new InputView(), new OutputView());

        PlannerController controller = new PlannerController(view, new PlannerService(), input);
        try {
            Receipt receipt = controller.run();
            return receipt;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
