package com.ecom.app.repository;

import com.ecom.app.dto.ProductResponse;
import com.ecom.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();

    @Query("select p from products p where p.active=true and p.stockQuantity > 0 and LOWER(p.name) LIKE LOWER(CONCAT('%' , :keyword, '%'))")
    List<Product> searchProduct(@Param("keyword") String keyword);
}
