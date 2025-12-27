package com.example.webshop.repository;

import com.example.webshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(
            value = """
        SELECT DISTINCT p
        FROM Product p
        LEFT JOIN FETCH p.category
        LEFT JOIN FETCH p.images
        LEFT JOIN FETCH p.variants
    """,
            countQuery = """
        SELECT COUNT(p)
        FROM Product p
    """
    )
    Page<Product> findPageWithAll(Pageable pageable);


}