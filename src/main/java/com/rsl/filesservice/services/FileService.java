package com.rsl.filesservice.services;

import com.rsl.filesservice.responses.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.path}")
    private String path;
    private final HttpServletRequest request;

    public FileResponse getFile(String fileName) throws IOException {
        Path filePath = Path.of(path).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return new FileResponse(resource, contentType);
    }
}
