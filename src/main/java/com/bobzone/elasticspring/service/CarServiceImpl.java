package com.bobzone.elasticspring.service;

import com.bobzone.elasticspring.domain.Car;
import com.bobzone.elasticspring.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class CarServiceImpl implements CarService {

    private CarRepository repository;

    @Autowired
    public CarServiceImpl(final CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Car addCar(final Car car) {
        return repository.save(car);
    }

    @Override
    public void deleteCar(final Car car) {
        repository.delete(car);
    }

    @Override
    public Car findById(final String id) {
        return repository.findOne(id);
    }

    public Page<Car> findByBrand(final String brand, final Pageable pageable) {
        return repository.findByBrand(brand, pageable);
    }

    public List<Car> findByModel(final String model) {
        return repository.findByModel(model);
    }


}
