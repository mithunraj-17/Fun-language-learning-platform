package com.example.demo.repository;

import com.example.demo.entity.LearningGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface LearningGoalRepository extends JpaRepository<LearningGoal, Long> {

    // ✅ Find by goal name (case-insensitive, partial match)
    @Query("SELECT lg FROM LearningGoal lg WHERE LOWER(lg.goalName) LIKE LOWER(CONCAT('%', :goalName, '%'))")
    List<LearningGoal> findByGoalName(@Param("goalName") String goalName);

    // ✅ Find by user ID
    @Query("SELECT lg FROM LearningGoal lg WHERE lg.userId = :userId")
    List<LearningGoal> findByUserId(@Param("userId") Long userId);

    // ✅ Find by target completion date
    @Query("SELECT lg FROM LearningGoal lg WHERE lg.targetCompletionDate = :targetCompletionDate")
    List<LearningGoal> findByTargetCompletionDate(@Param("targetCompletionDate") LocalDate targetCompletionDate);

    // ✅ Find by goal name AND user ID
    @Query("SELECT lg FROM LearningGoal lg WHERE LOWER(lg.goalName) LIKE LOWER(CONCAT('%', :goalName, '%')) AND lg.userId = :userId")
    List<LearningGoal> findByGoalNameAndUserId(@Param("goalName") String goalName, @Param("userId") Long userId);

    // ✅ Find by goal name AND target completion date
    @Query("SELECT lg FROM LearningGoal lg WHERE LOWER(lg.goalName) LIKE LOWER(CONCAT('%', :goalName, '%')) AND lg.targetCompletionDate = :targetCompletionDate")
    List<LearningGoal> findByGoalNameAndTargetCompletionDate(@Param("goalName") String goalName, @Param("targetCompletionDate") LocalDate targetCompletionDate);

    // ✅ Find by user ID AND target completion date
    @Query("SELECT lg FROM LearningGoal lg WHERE lg.userId = :userId AND lg.targetCompletionDate = :targetCompletionDate")
    List<LearningGoal> findByUserIdAndTargetCompletionDate(@Param("userId") Long userId, @Param("targetCompletionDate") LocalDate targetCompletionDate);

    // ✅ Find by ALL attributes (goal name, user ID, target completion date)
    @Query("SELECT lg FROM LearningGoal lg WHERE LOWER(lg.goalName) LIKE LOWER(CONCAT('%', :goalName, '%')) " +
           "AND lg.userId = :userId AND lg.targetCompletionDate = :targetCompletionDate")
    List<LearningGoal> findByAllAttributes(@Param("goalName") String goalName, @Param("userId") Long userId, @Param("targetCompletionDate") LocalDate targetCompletionDate);
}
