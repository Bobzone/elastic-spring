package com.bobzone.elasticspring.repository;

import com.bobzone.elasticspring.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

interface CarRepository extends ElasticsearchRepository<Car, Long> {

    Page<Car> findByBrand(String brand, Pageable pageable);

    List<Car> findByModel(String model);
    
}
