package com.excilys.training.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.excilys.training.configuration.ProjectConfig;
import com.excilys.training.configuration.SpringRootConfig;
import com.excilys.training.configuration.SpringWebConfig;

public class WebMVCApplicationInitializer extends
AbstractAnnotationConfigDispatcherServletInitializer {

@Override
protected Class<?>[] getRootConfigClasses() {
return new Class[] { SpringRootConfig.class };
}

@Override
protected Class<?>[] getServletConfigClasses() {
return new Class[] { SpringWebConfig.class };
}

@Override
protected String[] getServletMappings() {
return new String[] { "/" };
}

}