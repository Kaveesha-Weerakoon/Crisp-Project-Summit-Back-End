package com.example.Crisp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartResponsedto {
    private String id;
    private String cartId;
    private int qunatity;
    private String name;
    private float price;
    private String photoUrl;


}
