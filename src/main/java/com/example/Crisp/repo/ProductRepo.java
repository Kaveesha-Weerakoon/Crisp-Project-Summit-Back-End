package com.example.Crisp.repo;

import com.example.Crisp.model.Product;
import com.example.Crisp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,String> {

    @Query("SELECT p FROM Product p WHERE p.categoryId = ?1")
    Page<Product> findProductsByCategory(String categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Page<Product> findProductsByName(String Name, Pageable pageable);
}
