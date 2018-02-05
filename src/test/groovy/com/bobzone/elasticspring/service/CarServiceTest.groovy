package com.bobzone.elasticspring.service

import com.bobzone.elasticspring.ElasticSpringApplication
import com.bobzone.elasticspring.domain.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

@Ignore
@SpringBootTest(classes = ElasticSpringApplication.class)
public class CarServiceTest extends Specification {

    @Autowired
    CarService service

    @Autowired
    ElasticsearchTemplate template

    def setup() {
        template.deleteIndex(Car.class);
        template.createIndex(Car.class);
        template.putMapping(Car.class);
        template.refresh(Car.class);
    }

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

    @Unroll
    def "Finding cars work"() {
        given:
        Car car = new Car.CarBuilder()
                .setBrand("BMW")
                .setModel("Z5")
                .setMileage(10000)
                .setProductionYear(205)
                .build()
        Car saved = service.addCar(car)
        when:
        Car found = service.findById(saved.getId())
        then:
        found.getId() == saved.getId()
    }

    @Unroll
    def "Deleting Cars work"() {
        given:
        Car car = new Car.CarBuilder()
                .setBrand("BMW")
                .setModel("Z5")
                .setMileage(10000)
                .setProductionYear(205)
                .build()
        Car saved = service.addCar(car)
        when:
        service.deleteCar(saved)
        Car found = service.findById(saved.getId())
        then:
        found == null
    }

}
