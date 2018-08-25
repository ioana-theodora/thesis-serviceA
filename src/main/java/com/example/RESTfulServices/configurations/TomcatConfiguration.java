package com.example.RESTfulServices.configurations;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class TomcatConfiguration {

    /**
     *
     * * AJP setup
     * *
     * * -in application.properties-
     * * tomcat.ajp.port=9092
     * * tomcat.ajp.remoteauthentication=false
     * * tomcat.ajp.enabled=true
     *
     * * -------------------------------------
     *
     * * HTTP2 setup
     * *
     * * -in application.properties-
     * * server.http2.enabled = true
     * * OR (tomcat.http2.enabled = true)
     *
     * */
    @Value("${tomcat.ajp.port}")
    int ajpPort;

    @Value("${tomcat.ajp.enabled}")
    boolean tomcatAjpEnabled;
    @Value("${tomcat.http2.enabled}")
    boolean tomcatHTTP2Enabled;

    @Bean
    @Primary
    public ConfigurableServletWebServerFactory servletContainer() {

        TomcatServletWebServerFactory tomcatConnector = new TomcatServletWebServerFactory();

        if(tomcatAjpEnabled){
            System.out.print("AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP AJP".toUpperCase()+"\n");
            Connector ajpConnector = new Connector("AJP/1.3");
            ajpConnector.setPort(ajpPort);
            ajpConnector.setSecure(false);
            ajpConnector.setAllowTrace(false);
            //ajpConnector.setScheme("http");
            /**
            *
            * Set the scheme that will be assigned to requests received through this connector.
            *
            * */
            //ajpConnector.setScheme("h2c");
            tomcatConnector.addAdditionalTomcatConnectors(ajpConnector);
        }
        if(tomcatHTTP2Enabled){
            System.out.print("HTTP/2 HTTP/2 HTTP/2 HTTP/2 HTTP/2 HTTP/2 HTTP/2 HTTP/2 HTTP/2 HTTP/2 HTTP/2 HTTP/2".toUpperCase()+"\n");
            tomcatConnector.addConnectorCustomizers(http2Connector -> {
                http2Connector.addUpgradeProtocol(new Http2Protocol());
                //http2Connector.setScheme("h2c");
            });
        }
        return tomcatConnector;
    }
}
