package com.example.java21.web.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * PUBLIC_INTERFACE
 * DTO for creating/updating an Answer.
 */
public class AnswerDto {
    @NotBlank
    private String content;

    public AnswerDto() {}

    public AnswerDto(String content) {
        this.content = content;
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
