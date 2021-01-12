package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne // order:member=다:1
    @JoinColumn(name="member_id") // foreign key == member_id
    private Member member;

    @OneToMany(mappedBy = "order") // 연관관계의 주인이 아님, OrderItem 테이블의 order 필드에 의해 맵핑됨
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="delivery_id") // 연관관계의 주인, foreign key == delivery_id
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING) // default가 EnumType.ORDINAL인데 이것은 숫자로 표현된다고 한다. 그러면 중간에 다른 상태가 끼면 CANCLE은 2에서 3이 되는 불상사가.. 그럼 망한다!
    private OrderStatus status; // 주문상태 [ORDER, CANCLE]
}
