package com.github.williamdlm.personalfhirserver.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SequenceGenerator(name = "SEQ_PACIENTE", sequenceName = "SEQ_PACIENTE", allocationSize = 1)
@Table(name = "paciente")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PACIENTE")
    @Column(name = "cd_paciente", nullable = false)
    private Long id;

    @Column(name = "nm_paciente")
    private String name;

    @Column(name = "dt_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;


}
