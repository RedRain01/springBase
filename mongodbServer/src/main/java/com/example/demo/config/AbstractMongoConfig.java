package com.example.demo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @Author: why
 * @Date: 2019/09/09 18:51
 * @Description:
 */
@Setter
@Getter
public abstract class AbstractMongoConfig
{
	// Mongo DB Properties
	private String host, database, username, password, authentication_database;
	private int port;

	/*
	 * Method that creates MongoDbFactory Common to both of the MongoDb connections
	 */
	public MongoDbFactory mongoDbFactory() throws Exception
	{
		String uriStr = String.format("mongodb://%s:%s@%s:%d/%s", username, password, host, port,
				authentication_database);
		MongoClientURI uri = new MongoClientURI(uriStr);
		MongoClient mongoClient = new MongoClient(uri);
		return new SimpleMongoDbFactory(mongoClient, database);
	}

	/*
	 * Factory method to create the MongoTemplate
	 */
	public abstract MongoTemplate getMongoTemplate() throws Exception;
}
