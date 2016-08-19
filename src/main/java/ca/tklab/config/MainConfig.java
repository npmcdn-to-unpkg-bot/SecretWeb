/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.tklab.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

/**
 * Main configuration class for the application. Turns on @Component scanning,
 * loads externalized application.properties, and sets up the database.
 * 
 * @author Craig Walls
 */
@Configuration
@ComponentScan(basePackages = "ca.tklab", excludeFilters = { @Filter(Configuration.class) })
@PropertySources({
	@PropertySource(value="classpath:application.properties", ignoreResourceNotFound=false),
	@PropertySource(value="classpath:local.properties", ignoreResourceNotFound=true)
})

@Import({ PersistenceContext.class})
public class MainConfig {
	

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment env;



}
