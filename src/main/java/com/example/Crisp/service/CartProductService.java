package com.example.Crisp.service;

import com.example.Crisp.dto.CartProductdto;
import com.example.Crisp.dto.Userdto;
import com.example.Crisp.exception.deleteprohibited.DeleteProhibited;
import com.example.Crisp.exception.notfound.NotFoundException;
import com.example.Crisp.model.CartProduct;
import com.example.Crisp.model.Product;
import com.example.Crisp.model.User;
import com.example.Crisp.repo.CartProductRepo;
import com.example.Crisp.repo.ProductRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CartProductService {

    @Autowired
    private CartProductRepo cartProductRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public CartProductdto addCartProduct(CartProductdto cartProductdto) {

        Product product = productRepo.findById(cartProductdto.getProductId())
                .orElseThrow(() -> new NotFoundException(cartProductdto.getProductId()));

        CartProduct existingProduct = cartProductRepo.findUserByCartIDItemID(cartProductdto.getCartId(), cartProductdto.getProductId());
        if (existingProduct != null) {

            existingProduct.setQuantity(cartProductdto.getQuantity());
            CartProduct updatedProduct = cartProductRepo.save(existingProduct);
            return modelMapper.map(updatedProduct, CartProductdto.class);
        }

        CartProduct cartProductEntity = modelMapper.map(cartProductdto, CartProduct.class);
        cartProductEntity.setProduct(product);
        CartProduct savedCartProduct = cartProductRepo.save(cartProductEntity);

        return modelMapper.map(savedCartProduct, CartProductdto.class);
    }


    public CartProductdto deleteCartProductByID(String id) {
        CartProduct cartProduct= cartProductRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        try {
            cartProductRepo.delete(cartProduct);
        } catch (Exception e) {
            throw new DeleteProhibited();
        }

        return modelMapper.map(cartProduct, CartProductdto.class);
    }


    public CartProductdto getCartProductsByID(String id){
        CartProduct cartProduct= cartProductRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return modelMapper.map(cartProduct, CartProductdto.class);

    }

}
