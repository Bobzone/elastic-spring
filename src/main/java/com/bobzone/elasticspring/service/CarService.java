package com.bobzone.elasticspring.service;

import com.bobzone.elasticspring.domain.Car;

interface CarService {

    Car addCar(Car car);

    void deleteCar(Car car);
}
