package com.rsl.filesservice.repositories;

import com.rsl.filesservice.entities.MonoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonoFileRepository extends JpaRepository<MonoFile, Long> {

}
