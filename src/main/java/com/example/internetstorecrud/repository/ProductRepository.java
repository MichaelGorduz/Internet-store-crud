package com.example.internetstorecrud.repository;

import com.example.internetstorecrud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByProductname(String productname);
    //select * from student where id= and name="
    public Product findByProductidAndProductname(Long productid, String productname);
    List<Product> findAllByOrderByPriceAsc();

}
