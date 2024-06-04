package com.rsl.filesservice.responses;

import lombok.Data;

@Data
public class FileEntityResponse {
    private String type;
    private String sourceUrl;
    private String size;
}
