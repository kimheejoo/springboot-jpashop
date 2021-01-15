package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // 스프링이랑 다 엮어서 실행하겠다.
@SpringBootTest // 스프링 컨테이너 안에서 실행하려고
@Transactional // test할때만 rollback
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Rollback(false) -> Transactional 하면 commit 안하고 rollback 함.. 그래서 insert가 안됨!
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);
//        try{
//            memberService.join(member2); // 예외가 반드시 발생해야한다.
//        }catch (IllegalStateException e){
//            return;
//        }

        //then
        fail("예외가 발생해야 한다."); // exception 발생하지 않으면 실패 메세지로 예외가 발생해야 한다. 라고 뜸 그리고 에러남
    }
}