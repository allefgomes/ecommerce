package com.allefgomes.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allefgomes.ecommerce.domain.RequestItem;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Integer> {

}
