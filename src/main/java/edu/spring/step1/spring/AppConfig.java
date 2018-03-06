package edu.spring.step1.spring;

import edu.spring.step1.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Bean
    public static PropertyPlaceholderConfigurer propertyConfigIn() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        Properties property = new Properties();

        Resource resource = new ClassPathResource("client.properties");

        configurer.setLocation(resource);
        configurer.setSystemPropertiesModeName("SYSTEM_PROPERTIES_MODE_OVERRIDE");
        configurer.setIgnoreResourceNotFound(true);
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

    @Bean
    public Client client() {
        Client client = new Client();
        client.setId(environment.getProperty("id"));
        client.setFullName(environment.getProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }

}
