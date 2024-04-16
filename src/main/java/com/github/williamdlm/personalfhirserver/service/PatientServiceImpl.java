package com.github.williamdlm.personalfhirserver.service;


import com.github.williamdlm.personalfhirserver.model.LocalPatient;
import com.github.williamdlm.personalfhirserver.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public LocalPatient findById(Long id){
         return patientRepository
                 .findById(id)
                 .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public LocalPatient createPatient(LocalPatient localPatient) {
        return patientRepository.save(localPatient);
    }

    @Override
    public void deletePatientById(Long id) {
      patientRepository.deleteById(id);
    }

    @Override
    public LocalPatient updatePatient(Long id, LocalPatient localPatient) {
        LocalPatient byId  = this.findById(id);
        byId = localPatient;
        return patientRepository.save(byId);
    }

}
