package com.helloboot;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;


//결국 웹 서버를 만들기 위한 빌드업이라고 본다.
//하드코딩하지말고 등록되어 있는것을 적극 활용하자.
public class HellobootApplication {
    public static void main(String[] args) {
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet",
                    new DispatcherServlet(applicationContext)
            ).addMapping("/*");
        });
        webServer.start();
    }

}