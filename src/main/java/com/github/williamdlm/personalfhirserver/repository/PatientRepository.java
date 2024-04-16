package com.github.williamdlm.personalfhirserver.repository;

import com.github.williamdlm.personalfhirserver.model.LocalPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<LocalPatient,Long> {
}
