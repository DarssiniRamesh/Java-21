package com.example.java21.repository;

import com.example.java21.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PUBLIC_INTERFACE
 * Repository for Question entities.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
