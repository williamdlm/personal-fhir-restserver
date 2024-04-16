package com.github.williamdlm.personalfhirserver.service;


import com.github.williamdlm.personalfhirserver.model.LocalPatient;

public interface PatientService {
    LocalPatient findById(Long id);

    LocalPatient createPatient(LocalPatient localPatient);

    void deletePatientById(Long id);

    LocalPatient updatePatient(Long id, LocalPatient localPatient);
}
