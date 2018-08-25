package com.example.RESTfulServices.controllers;


import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import okhttp3.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/airports")
public class AirportController {

    public void controllerEnterPoint(){
        System.out.println("\n-------- AirportController -------- ");
    }

    OkHttpClient clientHTTP1 = new OkHttpClient.Builder()
            .protocols(Arrays.asList(Protocol.HTTP_1_1))
            .build();

    /**
     *
     * H2_PRIOR_KNOWLEDGE -> h2c
     * The prior_knowledge => no upgrade negotiation needed
     *                     => using this means we are sure that the other client is using HTTP/2
     *
     * */
    OkHttpClient clientHTTP2 = new OkHttpClient.Builder()
            .protocols(Arrays.asList(Protocol.H2_PRIOR_KNOWLEDGE)) //it's h2c
            .build();

    RestTemplate http1Template = new RestTemplate(new OkHttp3ClientHttpRequestFactory(clientHTTP1));
    RestTemplate http2Template = new RestTemplate(new OkHttp3ClientHttpRequestFactory(clientHTTP2));


    @RequestMapping(method = GET, produces = "application/json")
    public ResponseEntity getAirports() throws IOException, URISyntaxException {
        controllerEnterPoint();
        System.out.println("\n ** You are in /airports endpoint ** ");

        /*Request request = new Request.Builder()
                .url("http://localhost:9001/flights/api/v1/airports")
                .get()
                .build();

        Response response = clientHTTP2.newCall(request).execute();

        System.out.println("Response headers: " + response.headers());*/


        /**
         *
         * You can call string() once because response body can be huge so OkHttp doesn’t store it in memory, it reads it as a stream from network when you need it.
         *
         * When you read body as a string() OkHttp downloads response body and returns it to you without keeping reference to the string, it can’t be downloaded twice
         * without new request.
         *
         *
         * So NO System.out.println("Response body: " + response.body().toString());  (or .string())
         *
         * */
        //return response.body().string();
        return http2Template.exchange(new URL("http://localhost:9001/flights/api/v1/airports").toURI(), HttpMethod.GET, null, String.class);

    }


    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getAirport(@PathVariable Long id) throws IOException, URISyntaxException {
        controllerEnterPoint();
        System.out.println("\n ** You are in /airports/" + id + " endpoint ** ");

        ResponseEntity r = null;
        try{
            r = http2Template.exchange(new URL("http://localhost:9001/flights/api/v1/airports" + id).toURI(), HttpMethod.GET, null, String.class);
        }catch (HttpClientErrorException e){

        }
        //return http2Template.exchange(new URL("http://localhost:9001/flights/api/v1/airports/" + id).toURI(), HttpMethod.GET, null, String.class);

    }

    /*@RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteSomething(@PathVariable Long id) {
        if (somethingService.deleteSomething(id) == null) {
            return new ResponseEntity("No \"Something\" found to delete for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity updateSomething(@PathVariable Long id, @RequestBody Something something) {
        something = somethingService.updateSomething(id, something);
        if (something == null) {
            return new ResponseEntity("No \"Something\" found to update for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(something, HttpStatus.OK);
    }*/


    @RequestMapping(value = "/index", method = GET)
    public String index() {
        return "Congratulations from BlogController.java";
    }
}
