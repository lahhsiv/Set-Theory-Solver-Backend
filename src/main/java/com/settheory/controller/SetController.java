package com.settheory.controller;

import com.settheory.model.OperationRequest;
import com.settheory.model.OperationResponse;
import com.settheory.service.SetService;
import com.settheory.util.SetParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/")
public class SetController {

    private final SetService setService;

    @Autowired
    public SetController(SetService setService) {
        this.setService = setService;
    }

    @PostMapping("/union")
    public ResponseEntity<?> union(@RequestBody OperationRequest request) {
        try {
            Set<Integer> A = SetParser.parseSet(request.getSetA());
            Set<Integer> B = SetParser.parseSet(request.getSetB());
            return ResponseEntity.ok(setService.union(A, B));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationResponse("Error", Collections.singletonList(e.getMessage())));
        }
    }

    @PostMapping("/intersection")
    public ResponseEntity<?> intersection(@RequestBody OperationRequest request) {
        try {
            Set<Integer> A = SetParser.parseSet(request.getSetA());
            Set<Integer> B = SetParser.parseSet(request.getSetB());
            return ResponseEntity.ok(setService.intersection(A, B));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationResponse("Error", Collections.singletonList(e.getMessage())));
        }
    }

    @PostMapping("/complement")
    public ResponseEntity<?> complement(@RequestBody OperationRequest request) {
        try {
            Set<Integer> A = SetParser.parseSet(request.getSetA());
            Set<Integer> U = SetParser.parseSet(request.getSetU());
            if (U == null || U.isEmpty()) {
                throw new IllegalArgumentException("Universal Set U is required for Complement operation.");
            }
            return ResponseEntity.ok(setService.complement(A, U));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationResponse("Error", Collections.singletonList(e.getMessage())));
        }
    }

    @PostMapping("/cartesian")
    public ResponseEntity<?> cartesianProduct(@RequestBody OperationRequest request) {
        try {
            Set<Integer> A = SetParser.parseSet(request.getSetA());
            Set<Integer> B = SetParser.parseSet(request.getSetB());
            return ResponseEntity.ok(setService.cartesianProduct(A, B));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationResponse("Error", Collections.singletonList(e.getMessage())));
        }
    }

    @PostMapping("/powerset")
    public ResponseEntity<?> powerSet(@RequestBody OperationRequest request) {
        try {
            Set<Integer> A = SetParser.parseSet(request.getSetA());
            return ResponseEntity.ok(setService.powerSet(A));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationResponse("Error", Collections.singletonList(e.getMessage())));
        }
    }

    @PostMapping("/subset")
    public ResponseEntity<?> subsetCheck(@RequestBody OperationRequest request) {
        try {
            Set<Integer> A = SetParser.parseSet(request.getSetA());
            Set<Integer> B = SetParser.parseSet(request.getSetB());
            return ResponseEntity.ok(setService.subsetCheck(A, B));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationResponse("Error", Collections.singletonList(e.getMessage())));
        }
    }
}
