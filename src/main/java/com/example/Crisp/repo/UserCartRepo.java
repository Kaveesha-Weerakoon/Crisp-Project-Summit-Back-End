package com.example.Crisp.repo;

import com.example.Crisp.dto.CartResponsedto;
import com.example.Crisp.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCartRepo extends JpaRepository<com.example.Crisp.model.UserCart,String> {

    @Query(value = "SELECT * FROM user_cart WHERE user_id = :id", nativeQuery = true)
    List<UserCart>findByUserId(@Param("id") String id);

    @Query("SELECT new com.example.Crisp.dto.CartResponsedto(cp.id,cp.cartId, cp.quantity, cp.product.name, cp.product.price, cp.product.photoUrl) " +
            "FROM CartProduct cp " +
            "WHERE cp.cartId = :id")
    List<CartResponsedto> findByCartId(@Param("id") String id);

}
