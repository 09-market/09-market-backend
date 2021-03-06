package com.gonggu.market.api.domain.address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByZipcode(String zipcode);
}
