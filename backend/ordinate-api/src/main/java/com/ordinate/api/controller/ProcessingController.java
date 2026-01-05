package com.ordinate.api.controller;

import com.ordinate.api.dto.DocumentResponseDto;
import com.ordinate.api.dto.ProcessTextRequestDto;
import com.ordinate.api.service.TextProcessingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process")
public class ProcessingController {

    private final TextProcessingService textProcessingService;

    public ProcessingController(TextProcessingService textProcessingService) {
        this.textProcessingService = textProcessingService;
    }

    @PostMapping("/text")
    public DocumentResponseDto processText(@RequestBody ProcessTextRequestDto request) {
        return textProcessingService.processText(request);
    }
}