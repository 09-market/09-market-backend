package com.gonggu.market.api.domain.user;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.address.Address;
import com.gonggu.market.api.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String userImageUrl;
    private String userInfo;

    @ManyToOne(
            targetEntity = Address.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "address_id")
    private Address address;

    private String detailAddress;

    private int point;

    private String role;

    @OneToMany (
            targetEntity = Item.class,
            fetch = FetchType.EAGER,
            mappedBy = "user"
    )
    private List<Item> items = new ArrayList<>();

    public User() {
    }

    public User(Long id, String email, String password, String nickname, String mobile, String userImageUrl, String userInfo, Address address, String detailAddress, int point, String role, List<Item> items) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.userImageUrl = userImageUrl;
        this.userInfo = userInfo;
        this.address = address;
        this.detailAddress = detailAddress;
        this.point = point;
        this.role = role;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public int getPoint() {
        return point;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userImageUrl='" + userImageUrl + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", address=" + address +
                ", detailAddress='" + detailAddress + '\'' +
                ", point=" + point +
                ", role='" + role + '\'' +
                ", items=" + items +
                '}';
    }
}
