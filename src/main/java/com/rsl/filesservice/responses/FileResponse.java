package com.rsl.filesservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.Resource;

@Data
@AllArgsConstructor
public class FileResponse {
    private Resource resource;
    private String contentType;
}
