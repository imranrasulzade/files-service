package com.rsl.filesservice.services;

import com.rsl.filesservice.entities.MonoFile;
import com.rsl.filesservice.repositories.MonoFileRepository;
import com.rsl.filesservice.responses.FileResponse;
import com.rsl.filesservice.responses.MonoFileResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonoFileService {
    @Value("${url}")
    private String url;

    @Value("${file.path}")
    private String path;
    private final HttpServletRequest request;
    private final MonoFileRepository monoFileRepository;

    public MonoFileResponse uploadFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = Objects.requireNonNull(originalFilename)
                .substring(0, originalFilename.lastIndexOf(".")) + new Date().getTime() + originalFilename
                .substring(originalFilename.lastIndexOf("."));
        String filePath = System.getProperty("user.dir") + "/files/" + fileName;
        log.info("filePath: {}", filePath);
        file.transferTo(new File(filePath));
        MonoFile monoFile = new MonoFile(url + "/files/" + fileName);
        monoFileRepository.save(monoFile);

        MonoFileResponse monoFileResponse = new MonoFileResponse();
        monoFileResponse.setId(monoFile.getId());
        monoFileResponse.setSourceUrl(monoFile.getSourceUrl());
        return monoFileResponse;
    }


    public FileResponse getFile(String fileName) throws IOException {
        Path filePath = Path.of(path).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return new FileResponse(resource, contentType);
    }


}
