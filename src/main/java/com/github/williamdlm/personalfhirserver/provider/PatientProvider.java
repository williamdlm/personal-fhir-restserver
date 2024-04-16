package com.github.williamdlm.personalfhirserver.provider;


import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;
import com.github.williamdlm.personalfhirserver.converters.PatientConverter;
import com.github.williamdlm.personalfhirserver.model.LocalPatient;
import com.github.williamdlm.personalfhirserver.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PatientProvider implements IResourceProvider {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientConverter patientConverter;

    @Override
    public Class<Patient> getResourceType() {
        return Patient.class;
    }

    @Read
    public Patient readResourceById(@IdParam IdType id) {
        LocalPatient byId = patientService.findById(id.getIdPartAsLong());

        patientConverter.convert(byId);
        return patientConverter.convert(byId);
    }


    @Create
    public MethodOutcome createPatient(
            @ResourceParam Patient patient) {
        LocalPatient newLocalPatient = patientService.createPatient(
                patientConverter.reverse().convert(patient));
        MethodOutcome methodOutcome = new MethodOutcome();
        methodOutcome.setCreated(true);
        methodOutcome.setResource(patientConverter.convert(newLocalPatient));
        return methodOutcome;
    }

    @Update
    public MethodOutcome updatePatient(@IdParam IdType id,
                                       @ResourceParam Patient patient) {
        LocalPatient byId = patientService.findById(id.getIdPartAsLong());
        System.out.println(patient.getId());
        log.info("PATIENT GETID: " + patient.getIdElement().getIdPartAsLong());
        byId = patientService.updatePatient(id.getIdPartAsLong(),
                                            patientConverter.reverse().convert(patient));
        MethodOutcome methodOutcome = new MethodOutcome();
        methodOutcome.setCreated(true);
        methodOutcome.setResource(patientConverter.convert(byId));
        return methodOutcome;
    }


}
