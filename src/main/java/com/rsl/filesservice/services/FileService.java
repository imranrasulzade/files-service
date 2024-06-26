package com.rsl.filesservice.services;

import com.rsl.filesservice.entities.FileEntity;
import com.rsl.filesservice.repositories.FileEntityRepository;
import com.rsl.filesservice.responses.FileEntityResponse;
import com.rsl.filesservice.responses.FileResponse;
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
public class FileService {
    @Value("${url}")
    private String url;

    @Value("${file.path}")
    private String path;
    private final HttpServletRequest request;

    private final FileEntityRepository fileEntityRepository;


    public FileEntityResponse uploadFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setSize(String.valueOf(file.getSize()));
        fileEntity.setType(file.getContentType());
        fileEntity.setSourceUrl(getUrl(file));
        fileEntity = fileEntityRepository.save(fileEntity);

        FileEntityResponse fileEntityResponse = new FileEntityResponse();
        fileEntityResponse.setSize(fileEntity.getSize());
        fileEntityResponse.setType(fileEntity.getType());
        fileEntityResponse.setSourceUrl(fileEntity.getSourceUrl());
        return fileEntityResponse;
    }

    private String getUrl(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = Objects.requireNonNull(originalFilename)
                .substring(0, originalFilename.lastIndexOf(".")) + new Date().getTime() + originalFilename
                .substring(originalFilename.lastIndexOf("."));
        String filePath = System.getProperty("user.dir") + "/files/" + fileName;
        log.info("fileService.getFile() - fileName: {}, filePath: {}", fileName, filePath);
        file.transferTo(new File(filePath));
        return url + "/files/" + fileName;
    }


    public FileResponse getFile(String fileName) throws IOException {
        Path filePath = Path.of(path).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return new FileResponse(resource, contentType);
    }
}
