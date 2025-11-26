package com.example.java21.web;

import com.example.java21.domain.Answer;
import com.example.java21.domain.Question;
import com.example.java21.service.QuestionService;
import com.example.java21.web.dto.*;
import com.example.java21.web.mapper.QuestionMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * PUBLIC_INTERFACE
 * REST controller for Questions and nested Answers.
 */
@RestController
@RequestMapping(path = "/questions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Questions", description = "CRUD operations for questions and their answers")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "List questions", description = "Returns a paginated list of questions.")
    @GetMapping
    public Page<QuestionResponse> list(@PageableDefault(size = 10) Pageable pageable,
                                       @RequestParam(name = "includeAnswers", defaultValue = "false") boolean includeAnswers) {
        return service.listQuestions(pageable)
                .map(q -> QuestionMapper.toResponse(q, includeAnswers));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Get question by id", description = "Returns a single question by its identifier.")
    @GetMapping("/{id}")
    public QuestionResponse get(@PathVariable Long id,
                                @RequestParam(name = "includeAnswers", defaultValue = "true") boolean includeAnswers) {
        Question q = service.getQuestionOrThrow(id);
        return QuestionMapper.toResponse(q, includeAnswers);
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Create question", description = "Creates a new question.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponse> create(@Valid @RequestBody QuestionDto dto) {
        Question q = new Question(dto.getTitle(), dto.getContent());
        Question saved = service.createQuestion(q);
        return ResponseEntity.created(URI.create("/questions/" + saved.getId()))
                .body(QuestionMapper.toResponse(saved, false));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Update question", description = "Updates an existing question by id.")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public QuestionResponse update(@PathVariable Long id, @Valid @RequestBody QuestionDto dto) {
        Question updated = service.updateQuestion(id, dto.getTitle(), dto.getContent());
        return QuestionMapper.toResponse(updated, false);
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Delete question", description = "Deletes a question by id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "List answers for a question", description = "Lists all answers for a given question.")
    @GetMapping("/{id}/answers")
    public List<AnswerResponse> listAnswers(@PathVariable("id") Long questionId) {
        List<Answer> answers = service.listAnswers(questionId);
        return answers.stream()
                .map(a -> new AnswerResponse(a.getId(), a.getContent(), a.getCreatedAt()))
                .toList();
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Add answer to a question", description = "Creates an answer for a specific question.")
    @PostMapping(path = "/{id}/answers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnswerResponse> addAnswer(@PathVariable("id") Long questionId,
                                                    @Valid @RequestBody AnswerDto dto) {
        Answer saved = service.addAnswer(questionId, new Answer(dto.getContent()));
        return ResponseEntity.created(URI.create("/questions/" + questionId + "/answers/" + saved.getId()))
                .body(new AnswerResponse(saved.getId(), saved.getContent(), saved.getCreatedAt()));
    }

    // PUBLIC_INTERFACE
    @Operation(summary = "Delete answer from a question", description = "Deletes an answer by id for a specific question.")
    @DeleteMapping("/{id}/answers/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Long questionId,
                                             @PathVariable Long answerId) {
        service.deleteAnswer(questionId, answerId);
        return ResponseEntity.noContent().build();
    }
}
