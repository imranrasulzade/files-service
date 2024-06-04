package com.rsl.filesservice.controller;

import com.rsl.filesservice.responses.FileResponse;
import com.rsl.filesservice.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "File Apis", description = "File APIs")
@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;


    @Operation(
            summary = "Retrieve all",
            description = "just descriptions.",
            tags = {"tutorials", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = FileResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getFiles(@PathVariable String fileName) throws IOException {
        FileResponse fileResponse = fileService.getFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName)
                .contentType(MediaType.parseMediaType(fileResponse.getContentType()))
                .body(fileResponse.getResource());

    }


    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }

}
