package com.achernyadvki.restaurant.repository;

import com.achernyadvki.restaurant.entity.ItemCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends CrudRepository<ItemCategory, Long> {

    boolean existsByNameLikeIgnoreCase(String name);

}
