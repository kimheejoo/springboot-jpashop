package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery") // 연관관계의 주인이 아님.
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // default는 Enumtype.ORDINAL이고 얘는 숫자로 표현된다. 따라서 다른 상태의 추가를 염두해두고 STRING으로 하자.
    private DeliveryStatus status; // READY, COMP
}
