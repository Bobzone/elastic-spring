package com.bobzone.elasticspring.service

import com.bobzone.elasticspring.domain.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
public class CarServiceTest extends Specification {

    @Autowired
    CarService service;

    @Unroll
    def "Adding Cars work"() {
        given:
        Car car = new Car.CarBuilder()
                .setBrand("BMW")
                .setModel("Z5")
                .setMileage(10000)
                .setProductionYear(205)
                .build()
        when:
        Car result = service.addCar(car)
        then:
        null != result.getId()
    }

}
