package com.example.demo.service;

import com.example.demo.entity.LearningGoal;
import com.example.demo.repository.LearningGoalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningGoalService {
    private final LearningGoalRepository learningGoalRepository;

    public LearningGoalService(LearningGoalRepository learningGoalRepository) {
        this.learningGoalRepository = learningGoalRepository;
    }

    // ✅ Get learning goals with pagination and sorting
    public List<LearningGoal> getAllLearningGoals(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<LearningGoal> learningGoalPage = learningGoalRepository.findAll(pageable);
        return learningGoalPage.getContent(); // Returns only the list of LearningGoals
    }

    // ✅ Get LearningGoal by ID
    public Optional<LearningGoal> getLearningGoalById(Long id) {
        return learningGoalRepository.findById(id);
    }

    // ✅ Create a new LearningGoal
    public LearningGoal saveLearningGoal(LearningGoal learningGoal) {
        return learningGoalRepository.save(learningGoal);
    }

    // ✅ Update an existing LearningGoal
    public LearningGoal updateLearningGoal(Long id, LearningGoal updatedGoal) {
        return learningGoalRepository.findById(id).map(goal -> {
            goal.setGoalName(updatedGoal.getGoalName());
            goal.setTargetCompletionDate(updatedGoal.getTargetCompletionDate());
            goal.setUser(updatedGoal.getUser()); // Fix: Set User object instead of userId
            return learningGoalRepository.save(goal);
        }).orElseThrow(() -> new RuntimeException("Learning Goal not found with id: " + id));
    }

    // ✅ Delete a LearningGoal
    public void deleteLearningGoal(Long id) {
        if (!learningGoalRepository.existsById(id)) {
            throw new RuntimeException("Learning Goal not found with id: " + id);
        }
        learningGoalRepository.deleteById(id);
    }
}
