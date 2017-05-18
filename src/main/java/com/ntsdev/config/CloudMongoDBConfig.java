package com.ntsdev.config;


import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;

/**
 * Defines the service name for the MongoDB connection.  Searches Spring Cloud Connectors
 * for a suitable service named "springdays" that can provide a MongoDBFactory.
 */
@Configuration
@Profile("cloud")
public class CloudMongoDBConfig extends AbstractCloudConfig {
    @Bean
    public MongoDbFactory mongoFactory() {
        return connectionFactory().mongoDbFactory("springdays");
    }
}
