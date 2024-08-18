package com.example.Crisp.dto;

import com.example.Crisp.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartProductdto {

    @NotNull
    private String id;

    @NotNull
    private String productId;

    @NotNull
    private int quantity;

    @NotNull
    private String cartId;
}
