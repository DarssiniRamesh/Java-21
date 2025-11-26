package com.example.java21.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * PUBLIC_INTERFACE
 * DTO for creating/updating a Question.
 */
public class QuestionDto {
    @NotBlank
    @Size(max = 255)
    private String title;
    @NotBlank
    private String content;

    public QuestionDto() {}

    public QuestionDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}
