package com.company.project.services;

import com.company.project.dto.AccidentDTO;
import com.company.project.entity.Accident;
import com.company.project.mapper.AccidentMapper;
import com.company.project.repository.AccidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public AccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public AccidentDTO saveAccident(AccidentDTO accidentDTO) {
        Accident accident = AccidentMapper.toEntity(accidentDTO);
        Accident savedAccident = accidentRepository.save(accident);
        return AccidentMapper.toDTO(savedAccident);
    }

    public AccidentDTO getAccidentById(Long id) {
        return accidentRepository.findById(id)
                .map(AccidentMapper::toDTO)
                .orElse(null);
    }

    public List<AccidentDTO> getAllAccidents() {
        return accidentRepository.findAll().stream()
                .map(AccidentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteAccidentById(Long id) {
        accidentRepository.deleteById(id);
    }
}