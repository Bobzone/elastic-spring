package com.bobzone.elasticspring.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(indexName = "car-index", type = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final String brand;

    private final String model;

    private final int productionYear;

    private final long mileage;

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public long getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", productionYear=").append(productionYear);
        sb.append(", mileage=").append(mileage);
        sb.append('}');
        return sb.toString();
    }

    private Car(CarBuilder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.productionYear = builder.productionYear;
        this.mileage = builder.mileage;
    }

    public static class CarBuilder {
        private String brand;
        private String model;
        private int productionYear;
        private long mileage;

        public CarBuilder setBrand(final String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder setModel(final String model) {
            this.model = model;
            return this;
        }

        public CarBuilder setProductionYear(final int productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public CarBuilder setMileage(final long mileage) {
            this.mileage = mileage;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
