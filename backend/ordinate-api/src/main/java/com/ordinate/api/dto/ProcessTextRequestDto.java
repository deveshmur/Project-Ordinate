package com.ordinate.api.dto;

import com.ordinate.api.domain.TemplateKey;

public record ProcessTextRequestDto(
        TemplateKey templateKey,
        String rawText
) {}
