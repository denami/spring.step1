package edu.spring.step1.spring;

import edu.spring.step1.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;


@Configuration
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Properties property = new Properties();
        property.setProperty("ignoreResourceNotFound", "true");
        property.setProperty("systemPropertiesModeName", "SYSTEM_PROPERTIES_MODE_OVERRIDE");
        configurer.setProperties(property);
        return configurer;
    }

    @Autowired
    private Environment environment;

    @Bean
    public Date newDate() {
        return new Date();
    }

    @Bean
    public DateFormat dataFormat() {
        return DateFormat.getDateTimeInstance();
    }

    public Client client() {
        Client client = new Client();
        client.setId(environment.getProperty("id"));
        client.setFullName(environment.getProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }

}
