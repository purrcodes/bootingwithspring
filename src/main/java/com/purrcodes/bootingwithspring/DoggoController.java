package com.purrcodes.bootingwithspring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DoggoController {
    private static Map<String,Doggo> doggoRepo = new HashMap<>();
    static {
        Doggo golden = new Doggo();
        golden.setId(1);
        golden.setName("Golden Retriever");
        doggoRepo.put("Doggo 1",golden);

        Doggo labrador = new Doggo();
        labrador.setId(2);
        labrador.setName("Labrador Retriever");
        doggoRepo.put("Doggo 2",labrador);
    }

    @RequestMapping(value = "/Doggos", method = RequestMethod.GET)
    public ResponseEntity<Object> getDoggo() {
        return new ResponseEntity<>((doggoRepo.values()), HttpStatus.OK);
    }

    @RequestMapping(value = "/Doggos", method = RequestMethod.POST)
    public ResponseEntity<Object>  createDoggo(@RequestBody Doggo doggo) {
        doggoRepo.put(doggo.getName(),doggo);
        return new ResponseEntity<Object>("Doggo created succesfully",HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Doggos/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> putDoggo(@PathVariable("name") String name, @RequestBody Doggo doggo){
        if(doggoRepo.containsKey(name)) throw new DoggoNotFoundException();
            doggoRepo.remove(name);
            doggo.setName(name);
            doggoRepo.put(name,doggo);
            return new ResponseEntity<>("Product is successfully updated",HttpStatus.OK);
    }

    @RequestMapping(value = "/Doggos/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDoggo(@PathVariable("name") String name) {
            doggoRepo.remove(name);
            return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }

}
