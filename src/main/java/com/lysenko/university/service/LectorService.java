package com.lysenko.university.service;

import com.lysenko.university.model.Lector;
import com.lysenko.university.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LectorService {

    private final LectorRepository lectorRepository;

    public List<Lector> findAll() {
        return lectorRepository.findAll();
    }

    public Lector save(Lector lector) {
        return lectorRepository.save(lector);
    }

    public Lector findById(Long id) {
        return lectorRepository.findById(id).orElse(null);
    }

    public List<Lector> findByTemplate(String template) {
        return lectorRepository.findByTemplate(template);
    }
}
