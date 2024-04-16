package com.github.williamdlm.personalfhirserver.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import com.github.williamdlm.personalfhirserver.provider.PatientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

//https://github.com/FirelyTeam/fhirstarters/blob/master/java/hapi-fhirstarters-rest-server-skeleton/src/main/java/ca/uhn/example/servlet/ExampleRestfulServlet.java
/**
 * This servlet is the actual FHIR server itself
 */
public class BaseRestfulServlet extends RestfulServer {

    private static final long serialVersionUID = 1L;

    @Autowired
    ApplicationContext myApplicationContext;

    /**
     * Constructor
     */
    public BaseRestfulServlet() {
        super(FhirContext.forR4Cached()); // This is an R4 server
    }

    /**
     * This method is called automatically when the
     * servlet is initializing.
     */
    @Override
    public void initialize() {
        /*
         * Two resource providers are defined. Each one handles a specific
         * type of resource.
         */
        List<IResourceProvider> providers = new ArrayList<>();
        providers.add(myApplicationContext.getBean(PatientProvider.class));
        setResourceProviders(providers);

        /*
         * Use a narrative generator. This is a completely optional step,
         * but can be useful as it causes HAPI to generate narratives for
         * resources which don't otherwise have one.
         */
        INarrativeGenerator narrativeGen = new DefaultThymeleafNarrativeGenerator();
        getFhirContext().setNarrativeGenerator(narrativeGen);

        /*
         * Use nice coloured HTML when a browser is used to request the content
         */
        registerInterceptor(new ResponseHighlighterInterceptor());

    }

}