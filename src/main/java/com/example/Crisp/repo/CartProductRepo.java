package com.example.Crisp.repo;

import com.example.Crisp.model.CartProduct;
import com.example.Crisp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartProductRepo extends JpaRepository<CartProduct,String> {



    @Query(value = "SELECT * FROM cart_product WHERE cart_id = :id AND product_id = :productId", nativeQuery = true)
    CartProduct findUserByCartIDItemID(@Param("id") String id, @Param("productId") String productId);


}
