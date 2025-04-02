package com.example.demo.repository;

import com.example.demo.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Correct way to query by course ID
    List<Enrollment> findByCourse_Id(Long courseId);

    // Alternative JPQL Query
    @Query("SELECT e FROM Enrollment e WHERE e.course.id = :courseId")
    List<Enrollment> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT e FROM Enrollment e WHERE e.userId = :userId")
    List<Enrollment> findByUserId(@Param("userId") Long userId);

   
    @Query("SELECT e FROM Enrollment e WHERE e.progressPercentage >= :progressPercentage")
    List<Enrollment> findByProgressPercentage(@Param("progressPercentage") double progressPercentage);

    @Query("SELECT e FROM Enrollment e WHERE e.enrollmentDate = :date")
    List<Enrollment> findByEnrollmentDate(@Param("date") java.time.LocalDate date);
}
