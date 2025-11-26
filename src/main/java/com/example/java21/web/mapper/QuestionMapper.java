package com.example.java21.web.mapper;

import com.example.java21.domain.Answer;
import com.example.java21.domain.Question;
import com.example.java21.web.dto.AnswerResponse;
import com.example.java21.web.dto.QuestionResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PUBLIC_INTERFACE
 * Mapper for converting entities to API response models.
 */
public final class QuestionMapper {
    private QuestionMapper() {}

    // PUBLIC_INTERFACE
    public static QuestionResponse toResponse(Question q, boolean includeAnswers) {
        List<AnswerResponse> answers = null;
        if (includeAnswers && q.getAnswers() != null) {
            answers = q.getAnswers().stream()
                    .map(QuestionMapper::toResponse)
                    .collect(Collectors.toList());
        }
        return new QuestionResponse(q.getId(), q.getTitle(), q.getContent(), q.getCreatedAt(), answers);
    }

    private static AnswerResponse toResponse(Answer a) {
        return new AnswerResponse(a.getId(), a.getContent(), a.getCreatedAt());
    }
}
