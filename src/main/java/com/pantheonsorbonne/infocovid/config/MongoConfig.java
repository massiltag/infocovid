package com.pantheonsorbonne.infocovid.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

/**
 * Configuration MongoDB
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.pantheonsorbonne.infocovid.repositories")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "scoovidoo";
    }

    @Override
    public MongoClient mongoClient() {
        // Décommenter la ligne voulue

        // Base MongoDB locale :
        // ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");

        // Cluster MongoDB Atlas (Cloud) :
        ConnectionString connectionString = new ConnectionString("mongodb+srv://app:app@scoovidoo.9hkfs.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.pantheonsorbonne");
    }
}