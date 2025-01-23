package com.company.project.dto;

import java.time.LocalDateTime;

public class AccidentDTO {
    private Long id;
    private LocalDateTime accidentAt;

    // Constructor
    public AccidentDTO(Long id, LocalDateTime accidentAt) {
        this.id = id;
        this.accidentAt = accidentAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAccidentAt() {
        return accidentAt;
    }

    public void setAccidentAt(LocalDateTime accidentAt) {
        this.accidentAt = accidentAt;
    }
}