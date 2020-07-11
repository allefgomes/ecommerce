package com.allefgomes.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allefgomes.ecommerce.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
