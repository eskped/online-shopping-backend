package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Shoe;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.data.payloads.request.ShoeRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component // shorthand for beam registers interface as bean
public interface ShoeService {
    MessageResponse createShoe(ShoeRequest shoeRequest);
    Optional<Shoe> updateShoe(Integer shoeId, ShoeRequest shoeRequest);
    void deleteShoe(Integer shoeId);
    Shoe getASingleShoe(Integer shoeId);
    List<Shoe> getAllShoe();

}
