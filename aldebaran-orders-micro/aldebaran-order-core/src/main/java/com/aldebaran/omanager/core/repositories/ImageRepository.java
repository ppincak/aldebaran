package com.aldebaran.omanager.core.repositories;

import com.aldebaran.omanager.core.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {


}