package com.company.project.mapper;

import com.company.project.entity.Accident;
import com.company.project.dto.AccidentDTO;

public class AccidentMapper {
    private AccidentMapper() {
        // private constructor to hide the implicit public one
    }
    public static AccidentDTO toDTO(Accident accident) {
        return new AccidentDTO(accident.getId(), accident.getAccidentAt());
    }

    public static Accident toEntity(AccidentDTO accidentDTO) {
        Accident accident = new Accident();
        accident.setId(accidentDTO.getId());
        accident.setAccidentAt(accidentDTO.getAccidentAt());
        return accident;
    }
}