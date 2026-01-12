package com.ordinate.api.controller;

import com.ordinate.api.dto.AudioUploadResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/voice")
public class VoiceController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AudioUploadResponseDto upload(@RequestPart("audio") MultipartFile audio) {
        return new AudioUploadResponseDto(
                audio.getOriginalFilename(),
                audio.getContentType(),
                audio.getSize()
        );
    }
}
