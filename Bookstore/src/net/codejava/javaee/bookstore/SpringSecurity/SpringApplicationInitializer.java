package net.codejava.javaee.bookstore.SpringSecurity;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import net.codejava.javaee.bookstore.SpringConfig.BeanConfig;
import net.codejava.javaee.bookstore.SpringConfig.PersistenceJPAConfig;
import net.codejava.javaee.bookstore.SpringConfig.SecurityConfig;
import net.codejava.javaee.bookstore.SpringConfig.WebAppConfig;
import net.codejava.javaee.bookstore.servlets.ControllerServlet;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import java.net.URL;


public class SpringApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(WebAppConfig.class);
        ctx.register(SecurityConfig.class);
        ctx.register(PersistenceJPAConfig.class);

        container.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(container);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        /*ConfigurableEnvironment env = ctx.getEnvironment();
        ClassLoader loader = SpringApplicationInitializer.class.getClassLoader();
        String classpathStr = System.getProperty("java.class.path");
        URL sss1 = loader.getResource("net//codejava//javaee//bookstore//SpringSecurity//SpringApplicationInitializer.class");
        */

         FilterRegistration.Dynamic encodingFilter = container.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }
}
