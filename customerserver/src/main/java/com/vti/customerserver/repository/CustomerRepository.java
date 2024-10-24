package com.vti.customerserver.repository;

import com.vti.customerserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer WHERE mobile_number = :mobileNumber", nativeQuery = true)
    Customer findByMobileNumber(@Param("mobileNumber") String mobileNumber);

    Optional<Customer> findByEmail(String email);
}
