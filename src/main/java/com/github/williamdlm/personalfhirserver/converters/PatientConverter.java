package com.github.williamdlm.personalfhirserver.converters;

import com.github.williamdlm.personalfhirserver.model.LocalPatient;
import com.github.williamdlm.personalfhirserver.repository.PatientRepository;
import com.google.common.base.Converter;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PatientConverter extends Converter<LocalPatient, Patient> {

    @Autowired
    PatientRepository patientRepository;

    @Override
    protected Patient doForward(LocalPatient localPatient) {
        Patient patient = new Patient();

        patient.setId(localPatient.getId().toString());
        patient.addName().addGiven(localPatient.getName());

        Date birthDate = localPatient.getBirthDate();
        if (birthDate != null)
            patient.setBirthDate(birthDate);


        return patient;
    }

    @Override
    protected LocalPatient doBackward(Patient patientFhir) {
        LocalPatient patient = new LocalPatient();
        patient.setId(patientFhir.getIdElement().getIdPartAsLong());
        List<HumanName> names = patientFhir.getName();
        patient.setName(names.get(0).getGiven().toString());
        patient.setBirthDate(patientFhir.getBirthDate());
        // Adicione outras conversões de campos Java para FHIR aqui, se necessário
        return patient;
    }
}
