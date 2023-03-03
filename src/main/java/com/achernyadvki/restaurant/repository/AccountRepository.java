package com.achernyadvki.restaurant.repository;

import com.achernyadvki.restaurant.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {



}
