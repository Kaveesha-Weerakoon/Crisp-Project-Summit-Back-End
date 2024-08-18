package com.example.Crisp.dto;

import com.example.Crisp.model.CartProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCartdto {
    @Id
    @UuidGenerator
    @Column(name= "id", unique = true, updatable = false)
    private String id;

    @NotNull
    private String userId;
}
