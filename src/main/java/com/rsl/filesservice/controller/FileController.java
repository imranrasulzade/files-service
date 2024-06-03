package com.rsl.filesservice.controller;

import com.rsl.filesservice.responses.FileResponse;
import com.rsl.filesservice.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getFiles(@PathVariable String fileName) throws IOException {
        FileResponse fileResponse = fileService.getFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName)
                .contentType(MediaType.parseMediaType(fileResponse.getContentType()))
                .body(fileResponse.getResource());

    }

}
