package com.achernyadvki.restaurant.repository;

import com.achernyadvki.restaurant.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
