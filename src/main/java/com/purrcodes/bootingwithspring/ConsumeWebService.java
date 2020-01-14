package com.purrcodes.bootingwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ConsumeWebService {

    // Rest Template is used for consuming a web service, REST API

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/template/doggos")
    public String getDoggosList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("http://localhost:8008/Doggos", HttpMethod.GET, entity, String.class).getBody();
    }

    @RequestMapping(value = "/template/doggos", method = RequestMethod.POST)
    public String createProducts(@RequestBody Doggo doggo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Doggo> entity = new HttpEntity<Doggo>(doggo,headers);

        return restTemplate.exchange(
                "http://localhost:8008/Doggos", HttpMethod.POST, entity, String.class).getBody();
    }
    @RequestMapping(value = "/template/doggos/{name}", method = RequestMethod.PUT)
    public String updateProduct(@PathVariable("name") String name, @RequestBody Doggo doggo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Doggo> entity = new HttpEntity<Doggo>(doggo,headers);

        return restTemplate.exchange(
                "http://localhost:8008/Doggos/"+name, HttpMethod.PUT, entity, String.class).getBody();
    }
    @RequestMapping(value = "/template/doggos/{name}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("name") String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Doggo> entity = new HttpEntity<Doggo>(headers);

        return restTemplate.exchange(
                "http://localhost:8008/Doggos/"+name, HttpMethod.DELETE, entity, String.class).getBody();
    }

}
