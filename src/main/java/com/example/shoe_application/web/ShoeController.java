package com.example.shoe_application.web;

import com.example.shoe_application.data.models.Shoe;
import com.example.shoe_application.data.payloads.request.ShoeRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.service.ShoeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shoe")
public class ShoeController {

    @Autowired
    ShoeService shoeService;

    @GetMapping("/all")
    public ResponseEntity<List<Shoe>> getAllShoes() {
        List<Shoe> shoes = shoeService.getAllShoe();
        return new ResponseEntity<>(shoes, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Shoe> getShoeById(@PathVariable("id") Integer id) {
        Shoe shoe = shoeService.getASingleShoe(id);
        return new ResponseEntity<>(shoe, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addShoe( @RequestBody ShoeRequest shoe) {
        MessageResponse newShoe = shoeService.createShoe(shoe);
        return new ResponseEntity<>(newShoe, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public Optional<Shoe> updateShoe(@PathVariable Integer id, @RequestBody ShoeRequest shoe) {
        return shoeService.updateShoe(id, shoe);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShoe(@PathVariable("id") Integer id) {
        shoeService.deleteShoe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
