package com.github.williamdlm.personalfhirserver.service;


import com.github.williamdlm.personalfhirserver.model.Patient;
import com.github.williamdlm.personalfhirserver.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient findById(Long id){
         return patientRepository
                 .findById(id)
                 .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id) {
      patientRepository.deleteById(id);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        Patient byId  = this.findById(id);
        byId = patient;
        return patientRepository.save(byId);
    }

}
