package com.company.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.project.entity.Sonicsaver;

@Repository
public interface SonicsaverRepository extends JpaRepository<Sonicsaver, Long> {
    Optional<Sonicsaver> findById(int id);
}