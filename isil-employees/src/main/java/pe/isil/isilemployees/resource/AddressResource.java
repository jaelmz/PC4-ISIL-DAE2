package pe.isil.isilemployees.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.isilemployees.model.Address;
import pe.isil.isilemployees.service.AddressService;

import java.util.ArrayDeque;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressResource {
    @Autowired
    private AddressService addressService;

    //GET
    @GetMapping("/address")
    public ResponseEntity getAll(){
        List<Address> addresses = addressService.findAll();
        if(addresses == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(addresses,HttpStatus.OK);
    }

    //GET BY ID

    @GetMapping("/address/{id}")
    public ResponseEntity getById(@PathVariable Long add_id){

        Address currentAddress = addressService.findById(add_id);
        if(currentAddress == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(currentAddress, HttpStatus.OK);
    }

    @PostMapping("/address")
    public ResponseEntity create(@RequestBody Address address){
        addressService.create(address);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody Address address) {

        Address currentAddress = addressService.findById(id);
        if (currentAddress == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentAddress.setCountry(address.getCountry());
        currentAddress.setCity(address.getCity());
        currentAddress.setStreet(address.getStreet());

        addressService.update(currentAddress);

        return new ResponseEntity(currentAddress, HttpStatus.OK);
    }

        @DeleteMapping("/products/{id}")
        public ResponseEntity delete(@PathVariable Long id){
            Address currentAddress = addressService.findById(id);
            if(currentAddress == null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            addressService.delete(currentAddress.getAdd_id());
            return new ResponseEntity(HttpStatus.OK);
        }
    }
