package com.rsl.filesservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class MonoFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceUrl;
}
