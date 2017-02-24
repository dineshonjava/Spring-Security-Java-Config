/**
 * 
 */
package com.doj.app.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.doj.app.config.RootConfig;
import com.doj.app.config.security.SecurityConfig;
import com.doj.app.config.web.WebMvcConfig;

/**
 * @author Dinesh.Rajput
 *
 */
public class ApplicationInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
