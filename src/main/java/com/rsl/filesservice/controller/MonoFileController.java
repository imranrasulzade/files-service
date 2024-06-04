package com.rsl.filesservice.controller;

import com.rsl.filesservice.responses.FileResponse;
import com.rsl.filesservice.services.MonoFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mono-files")
public class MonoFileController {
    private final MonoFileService monoFileService;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getFiles(@PathVariable String fileName) throws IOException {
        FileResponse fileResponse = monoFileService.getFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName)
                .contentType(MediaType.parseMediaType(fileResponse.getContentType()))
                .body(fileResponse.getResource());

    }


    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.ok(monoFileService.uploadFile(file));
    }

}
