package com.aldebaran.omanager.core.repositories;

import com.aldebaran.omanager.core.entities.FileLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileLinkRepository extends JpaRepository<FileLink, Long> {


}