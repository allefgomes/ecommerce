package com.allefgomes.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allefgomes.ecommerce.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

}
