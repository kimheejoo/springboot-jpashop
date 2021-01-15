package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor // final 붙은 필드만 가지고 생성자를 만들어줌, 그리고 생성자 하나만 있으면 autowired안해도 알아서 빈으로 등록된 entity manager를 em에 주입해줌!
public class MemberRepository {

//    @PersistenceContext //entity manager를 생성해서 주입해준다.
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
    public List<Member> findAll(){ //jpql 사용
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
