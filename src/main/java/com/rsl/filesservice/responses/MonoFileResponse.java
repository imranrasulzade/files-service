package com.rsl.filesservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonoFileResponse {
    private Long id;
    private String sourceUrl;
}
