package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String name;

    @Embedded // 내장타입이다
    private Address address;

    @OneToMany(mappedBy = "member") // member:order = 1:다, 연관관계의 주인이 아니다 -> mappedby, Order 테이블에 있는 member 필드에 의해 맵핑됐다.
    private List<Order> orders = new ArrayList<>();
}
