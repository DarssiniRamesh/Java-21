package com.example.java21.web.dto;

import java.time.OffsetDateTime;

/**
 * PUBLIC_INTERFACE
 * API response representation for Answer.
 */
public class AnswerResponse {
    private Long id;
    private String content;
    private OffsetDateTime createdAt;

    public AnswerResponse() {}

    public AnswerResponse(Long id, String content, OffsetDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getContent() { return content; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
