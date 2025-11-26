package com.example.java21.web.dto;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * PUBLIC_INTERFACE
 * API response representation for Question with optional answers.
 */
public class QuestionResponse {
    private Long id;
    private String title;
    private String content;
    private OffsetDateTime createdAt;
    private List<AnswerResponse> answers;

    public QuestionResponse() {}

    public QuestionResponse(Long id, String title, String content, OffsetDateTime createdAt, List<AnswerResponse> answers) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.answers = answers;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public List<AnswerResponse> getAnswers() { return answers; }
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public void setAnswers(List<AnswerResponse> answers) { this.answers = answers; }
}
