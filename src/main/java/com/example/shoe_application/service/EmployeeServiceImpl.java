package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Shoe;
import com.example.shoe_application.data.payloads.request.ShoeRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.data.repository.ShoeRepository;
import com.example.shoe_application.ShoeApplication.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements ShoeService {

    @Autowired
    ShoeRepository shoeRepository;

    @Override
    public MessageResponse createShoe(ShoeRequest shoeRequest) {
        Shoe newShoe = new Shoe();
        newShoe.setModel(shoeRequest.getModel());
        newShoe.setPrice(shoeRequest.getPrice());
        newShoe.setProducts(shoeRequest.getProducts());
        shoeRepository.save(newShoe);
        return new MessageResponse("New shoe created successfully");

    }

    @Override
    public Optional<Shoe> updateShoe(Integer shoeId, ShoeRequest shoeRequest) throws ResourceNotFoundException {
        Optional<Shoe> shoe = shoeRepository.findById(shoeId);
        if (shoe.isEmpty()){
            throw new ResourceNotFoundException("Shoe", "id", shoeId);
        }
        else {
            shoe.get().setModel(shoeRequest.getModel());
            shoe.get().setPrice(shoeRequest.getPrice());
            shoe.get().setProducts(shoeRequest.getProducts());
            shoeRepository.save(shoe.get());
        }
        return shoe;
    }

    @Override
    public void deleteShoe(Integer shoeId) {
        if (shoeRepository.getById(shoeId).getId().equals(shoeId)){
            shoeRepository.deleteById(shoeId);
        }
        else throw new ResourceNotFoundException("Employee", "id", employeeId);
    }

    @Override
    public Shoe getASingleShoe(Integer shoeId) {
        return null;
    }

    @Override
    public List<Shoe> getAllShoe() {
        return null;
    }
}
