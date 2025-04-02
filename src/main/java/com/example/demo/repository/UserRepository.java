package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //Fetch users with pagination & sorting using JPQL
    @Query("SELECT u FROM User u")
    Page<User> findAllUsers(Pageable pageable);

    // Fetch user by email
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    //Search users by name (case insensitive)
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> searchByName(@Param("name") String name);
    
    //Get all users who prefer a specific language
    @Query("SELECT u FROM User u WHERE u.preferredLanguage = :language")
    List<User> findByPreferredLanguage(@Param("language") String language);

    @Query("DELETE FROM User u WHERE u.name = :name")
    void deleteByName(@Param("name") String name);
}
