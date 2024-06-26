package com.rsl.filesservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@Data
@RequiredArgsConstructor
public class MonoFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceUrl;

    public MonoFile(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
