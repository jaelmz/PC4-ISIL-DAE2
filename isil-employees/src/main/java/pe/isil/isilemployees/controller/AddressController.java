package pe.isil.isilemployees.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.isilemployees.model.Address;
import pe.isil.isilemployees.service.AddressService;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/address")
    public String getAddress(Model model){
        model.addAttribute("address",addressService.findAll());
        return "address";
    }

    @PostMapping("/address/save")
    public String saveAddress(Address address, Model model)
    {
        addressService.create(address);

        List<Address> addresses = addressService.findAll();
        model.addAttribute("address",addresses);
        return "address";
    }

    @GetMapping("address/add")
    public String addAddress(Model model){
        model.addAttribute("address",new Address());
        return "address-add";
    }

    @GetMapping("/address/edit/{add_id}")
    public String getStudentForUpdate(@PathVariable Long add_id,
                                      Model model) {

        Address currentAddress = addressService.findById(add_id);
        model.addAttribute("address", currentAddress);
        return "address-edit";
    }

    @PostMapping("/address/update/{add_id}")
    public String updateAddress(@PathVariable Long add_id,
                                Address address,
                                Model model){
        addressService.update(address);

        List<Address> addresses = addressService.findAll();
        model.addAttribute("address",addresses);
        return "address";
    }

    @GetMapping("address/delete/{add_id}")
    public String deleteAddress(@PathVariable Long add_id,
                                Model model){
        Address currentAddress = addressService.findById(add_id);

        addressService.delete(add_id);

        List<Address> addresses = addressService.findAll();
        model.addAttribute("address",addresses);
        return "address";
    }
}
