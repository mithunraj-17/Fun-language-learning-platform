package com.example.demo.controller;

import com.example.demo.entity.LearningGoal;
import com.example.demo.service.LearningGoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-goals")
@CrossOrigin("*")
public class LearningGoalController {
    private final LearningGoalService learningGoalService;

    public LearningGoalController(LearningGoalService learningGoalService) {
        this.learningGoalService = learningGoalService;
    }

    // ✅ Get all learning goals with pagination and sorting
    @GetMapping
    public ResponseEntity<List<LearningGoal>> getAllLearningGoals(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size, 
            @RequestParam(defaultValue = "id") String sortBy, 
            @RequestParam(defaultValue = "asc") String sortOrder) {
        
        List<LearningGoal> learningGoals = learningGoalService.getAllLearningGoals(page, size, sortBy, sortOrder);
        return ResponseEntity.ok(learningGoals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningGoal> getLearningGoalById(@PathVariable Long id) {
        return learningGoalService.getLearningGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LearningGoal createLearningGoal(@RequestBody LearningGoal learningGoal) {
        return learningGoalService.saveLearningGoal(learningGoal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningGoal> updateLearningGoal(@PathVariable Long id, @RequestBody LearningGoal learningGoal) {
        LearningGoal updatedGoal = learningGoalService.updateLearningGoal(id, learningGoal);
        return ResponseEntity.ok(updatedGoal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearningGoal(@PathVariable Long id) {
        learningGoalService.deleteLearningGoal(id);
        return ResponseEntity.noContent().build();
    }
}
