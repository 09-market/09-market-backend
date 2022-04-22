package com.gonggu.market.api.domain.user;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.address.Address;

import javax.persistence.*;

@Entity
@Table(name = "zeronine_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String mobile;

    @ManyToOne(
            targetEntity = Address.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "address_id")
    private Address address;

    private String detailAddress;

    private int point;

    private String role;

    public User() {
    }

    public User(Long id, String email, String password, String nickname, String mobile, Address address, String detailAddress, int point, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.address = address;
        this.detailAddress = detailAddress;
        this.point = point;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public Address getAddress() {
        return address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public int getPoint() {
        return point;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
