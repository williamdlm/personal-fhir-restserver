package com.github.williamdlm.personalfhirserver;

import com.github.williamdlm.personalfhirserver.config.BaseRestfulServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {ElasticsearchRestClientAutoConfiguration.class})
public class PersonalFhirServerApplication {

    @Autowired
    AutowireCapableBeanFactory beanFactory;

    public static void main(String[] args) {
        SpringApplication.run(PersonalFhirServerApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<BaseRestfulServlet> hapiServletRegistration() {
        ServletRegistrationBean<BaseRestfulServlet> servletRegistrationBean = new ServletRegistrationBean<>();
        BaseRestfulServlet plainRestfulServer = new BaseRestfulServlet();
        beanFactory.autowireBean(plainRestfulServer);
        servletRegistrationBean.setServlet(plainRestfulServer);
        servletRegistrationBean.addUrlMappings("/fhir/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

}
