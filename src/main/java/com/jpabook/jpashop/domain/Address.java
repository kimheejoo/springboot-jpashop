package com.jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 어딘가 내장될 수 있다.
@Getter
//setter를 만들지 않는 이유는 얘네는 바뀔이유가 없는 아이들이라 생성할때만 값을 넣어두는게 맞다고 한다.(enumerate? 머.. 그런..)
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // jpa가 생성을 할 때, 프록시나 리플렉션이라는 기술을 사용해야 할 때가 많다
    // 근데 기본 생성자가 없으면 기술을 사용하지 못한다.
    // 그리고 public 으로 하면 다른 메쏘드들이 호출할 수 있으니 jpa 스펙상 엔티티나 임베디드 타입은 (여기서는 임베디드) protected가 public보다 안전하므로 protected로 설정한다.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
