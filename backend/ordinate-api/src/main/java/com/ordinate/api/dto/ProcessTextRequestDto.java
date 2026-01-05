package com.ordinate.api.dto;

import com.ordinate.api.domain.TemplateKey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProcessTextRequestDto(
        @NotNull TemplateKey templateKey,
        @NotBlank String rawText
) {}
