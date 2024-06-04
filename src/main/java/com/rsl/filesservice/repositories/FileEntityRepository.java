package com.rsl.filesservice.repositories;

import com.rsl.filesservice.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {

}
