/**
 * 
 */
package com.doj.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dinesh.Rajput
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.doj.app.service", "com.doj.app.repository"})
public class RootConfig {

}
