package com.aldebaran.order.core.repositories;

import com.aldebaran.order.core.entities.FileLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileLinkRepository extends JpaRepository<FileLink, Long> {


}