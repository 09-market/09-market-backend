package com.gonggu.market.api.domain.address;

import com.gonggu.market.api.domain.BaseEntity;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCitytown(String citytown) {
        this.citytown = citytown;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zipcode='" + zipcode + '\'' +
                ", citytown='" + citytown + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
