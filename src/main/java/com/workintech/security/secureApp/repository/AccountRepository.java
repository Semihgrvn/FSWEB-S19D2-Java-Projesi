package com.workintech.security.secureApp.repository;

import com.workintech.security.secureApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM account a WHERE a.name = :name")
    Optional<Account> findById(String name);

}
