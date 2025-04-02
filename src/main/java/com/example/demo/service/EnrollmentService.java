package com.example.demo.service;

import com.example.demo.entity.Enrollment;
import com.example.demo.repository.EnrollmentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // ✅ Get enrollments with pagination & sorting
    public List<Enrollment> getAllEnrollments(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Enrollment> enrollmentPage = enrollmentRepository.findAll(pageable);
        return enrollmentPage.getContent();
    }

    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(Long id, Enrollment updatedEnrollment) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setUser(updatedEnrollment.getUser());
            enrollment.setCourse(updatedEnrollment.getCourse());
            enrollment.setProgressPercentage(updatedEnrollment.getProgressPercentage());
            enrollment.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());
            return enrollmentRepository.save(enrollment);
        }).orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public List<Enrollment> findByProgressPercentage(double progressPercentage) {
        return enrollmentRepository.findByProgressPercentage(progressPercentage);
    }

    public List<Enrollment> getEnrollmentsByDate(LocalDate date) {
        return enrollmentRepository.findByEnrollmentDate(date);
    }
}
