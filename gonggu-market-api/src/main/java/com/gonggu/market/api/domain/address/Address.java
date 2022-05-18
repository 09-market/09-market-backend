package com.gonggu.market.api.domain.address;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.controller.dto.auth.SignupDto;
import com.gonggu.market.api.controller.dto.user.UserUpdateDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipcode;
    private String citytown;
    private String province;

    public Address() {
    }

    public Address(Long id, String zipcode, String citytown, String province) {
        this.id = id;
        this.zipcode = zipcode;
        this.citytown = citytown;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCitytown() {
        return citytown;
    }

    public String getProvince() {
        return province;
    }

    public static Address fromUserToAddress(SignupDto dto) {
        String fullAddress = dto.getAddress();
        String[] addressArray = fullAddress.split(" ");
        Address address = new Address(null, dto.getZipcode(), addressArray[0], addressArray[1]);
        return address;
    }

    public static Address fromUserToAddress(UserUpdateDto dto) {
        String fullAddress = dto.getAddress();
        String[] addressArray = fullAddress.split(" ");
        Address address = new Address(null, dto.getZipcode(), addressArray[0], addressArray[1]);
        return address;
    }
}
