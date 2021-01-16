package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = LAZY) // order:member=다:1, X의 경우 default가 EAGER 타입인데 얘는 즉시로딩이라 연관된 테이블 다 불러옴 그래서 안쓰는걸 권장
    @JoinColumn(name="member_id") // foreign key == member_id
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 연관관계의 주인이 아님, OrderItem 테이블의 order 필드에 의해 맵핑됨, cascade를 사용하기 때문에 persist 하면 알아서 다 저장되고 삭제도 삭제되면 다 같이 삭제.
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id") // 연관관계의 주인, foreign key == delivery_id
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING) // default가 EnumType.ORDINAL인데 이것은 숫자로 표현된다고 한다. 그러면 중간에 다른 상태가 끼면 CANCLE은 2에서 3이 되는 불상사가.. 그럼 망한다!
    private OrderStatus status; // 주문상태 [ORDER, CANCLE]

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
