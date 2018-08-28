package com.example.RESTfulServices.controllers;


import com.example.RESTfulServices.Utils;
import okhttp3.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/airports")
public class AirportController{

    @Autowired
    private RestTemplate restTemplate;

    public void controllerEnterPoint(){
        System.out.println("\n-------- AirportController -------- ");
    }




    @RequestMapping(method = GET, produces = "application/json")
    public ResponseEntity getAirports() throws IOException, URISyntaxException {
        controllerEnterPoint();


        System.out.println("\n ** You are in /airports endpoint ** ");

        return restTemplate.exchange(new URL("http://localhost:9001/flights/api/v1/airports").toURI(), HttpMethod.GET, null, String.class);

    }


    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getAirport(@PathVariable Long id) throws IOException, URISyntaxException {
        controllerEnterPoint();
        System.out.println("\n ** You are in /airports/" + id + " endpoint ** ");

        return restTemplate.exchange(new URL("http://localhost:9001/flights/api/v1/airports" + id).toURI(), HttpMethod.GET, null, String.class);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteAirport(@PathVariable Long id) throws IOException, URISyntaxException {
        controllerEnterPoint();
        System.out.println("\n ** You are in /airports/" + id + " endpoint ** ");

        return restTemplate.exchange(new URL("http://localhost:9001/flights/api/v1/airports" + id).toURI(), HttpMethod.DELETE, null, String.class);
    }

    /*@RequestMapping(value = "/{id}", method = PUT)
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
