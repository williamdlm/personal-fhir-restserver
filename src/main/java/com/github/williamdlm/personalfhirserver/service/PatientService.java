package com.github.williamdlm.personalfhirserver.service;


import com.github.williamdlm.personalfhirserver.model.Patient;

public interface PatientService {
    Patient findById(Long id);

    Patient createPatient(Patient patient);

    void deletePatientById(Long id);

    Patient updatePatient(Long id, Patient patient);
}
