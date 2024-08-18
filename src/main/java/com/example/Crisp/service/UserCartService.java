package com.example.Crisp.service;

import com.example.Crisp.dto.CartProductdto;
import com.example.Crisp.dto.CartResponsedto;
import com.example.Crisp.dto.UserCartdto;
import com.example.Crisp.dto.Userdto;
import com.example.Crisp.exception.notfound.NotFoundException;
import com.example.Crisp.model.User;
import com.example.Crisp.model.UserCart;
import com.example.Crisp.repo.UserCartRepo;
import com.example.Crisp.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserCartService {
    @Autowired
    private UserCartRepo userCartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UserCartdto addCart(UserCartdto userCartdto) {
        User user= userRepo.findById(userCartdto.getUserId())
                .orElseThrow(() -> new NotFoundException(userCartdto.getUserId()));

        UserCart savedCart =userCartRepo.save(modelMapper.map(userCartdto, UserCart.class));
        return modelMapper.map(savedCart, UserCartdto.class);
    }

    public UserCartdto deleteCartByID(String id){
        UserCart cart= userCartRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        userCartRepo.delete(cart);

        return modelMapper.map(cart, UserCartdto.class);

    }

    public List<UserCartdto> getCartBYUserId(String id){
        User user= userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        List<UserCart> cart= userCartRepo.findByUserId(id);


        return modelMapper.map(cart, new TypeToken<List<CartProductdto>>(){}.getType());

    }


    public List<CartResponsedto> getCartById(String id){
        UserCart userCart= userCartRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        List<CartResponsedto> cart= userCartRepo.findByCartId(id);

        return cart;
    }



}
