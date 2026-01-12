package com.ordinate.api.dto;

public record AudioUploadResponseDto(
        String originalFileName,
        String contentType,
        long sizeBytes
) {}
