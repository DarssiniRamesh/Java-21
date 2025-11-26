package com.example.java21.service;

import com.example.java21.domain.Answer;
import com.example.java21.domain.Question;
import com.example.java21.repository.AnswerRepository;
import com.example.java21.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Service providing CRUD operations for Questions and Answers.
 */
@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    // PUBLIC_INTERFACE
    public Page<Question> listQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    // PUBLIC_INTERFACE
    public Question getQuestionOrThrow(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found: " + id));
    }

    // PUBLIC_INTERFACE
    public Question createQuestion(Question q) {
        return questionRepository.save(q);
    }

    // PUBLIC_INTERFACE
    public Question updateQuestion(Long id, String title, String content) {
        Question q = getQuestionOrThrow(id);
        q.setTitle(title);
        q.setContent(content);
        return questionRepository.save(q);
    }

    // PUBLIC_INTERFACE
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found: " + id);
        }
        questionRepository.deleteById(id);
    }

    // PUBLIC_INTERFACE
    public List<Answer> listAnswers(Long questionId) {
        // validate question exists
        getQuestionOrThrow(questionId);
        return answerRepository.findByQuestionId(questionId);
    }

    // PUBLIC_INTERFACE
    public Answer addAnswer(Long questionId, Answer answer) {
        Question q = getQuestionOrThrow(questionId);
        answer.setQuestion(q);
        return answerRepository.save(answer);
    }

    // PUBLIC_INTERFACE
    public void deleteAnswer(Long questionId, Long answerId) {
        // validate question exists
        getQuestionOrThrow(questionId);
        Answer ans = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found: " + answerId));
        if (ans.getQuestion() == null || !ans.getQuestion().getId().equals(questionId)) {
            throw new ResourceNotFoundException("Answer " + answerId + " does not belong to question " + questionId);
        }
        answerRepository.delete(ans);
    }
}
