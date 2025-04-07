package com.example.demo.repository;

import com.example.demo.observer.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialCursosRepository extends JpaRepository<HistorialCursos, Long> {
}
