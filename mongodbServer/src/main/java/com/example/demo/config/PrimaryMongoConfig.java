
package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;


/**
 * @Author: why
 * @Date: 2019/09/09 18:51
 * @Description:
 */
@Configuration // Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.primary") // Defines my custom prefix and points to the
																	// secondary db properties
public class PrimaryMongoConfig extends AbstractMongoConfig
{

/**
	 * Implementation of the MongoTemplate factory method
	 * 
	 * @Bean gives a name (primaryMongoTemplate) to the created MongoTemplate
	 *       instance Note that this method doesn't have @Primary
	 */

	@Override
	public @Bean(name = "primaryMongoTemplate")
    MongoTemplate getMongoTemplate() throws Exception
	{
		MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory(), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		return new MongoTemplate(mongoDbFactory(), converter);
	}
}

