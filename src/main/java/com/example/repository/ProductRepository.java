package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ProductModel;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Long> {

}
