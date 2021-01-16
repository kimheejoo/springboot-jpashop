package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;

   @Test
   @Rollback(value = false)
   public void 상품등록() throws Exception{
       //given
       Book book = new Book();
       book.setAuthor("김희주");
       book.setIsbn("what?");

       //when
       itemService.saveItem(book);

       //then
       Assert.assertEquals(book,itemRepository.findOne(book.getId()));
   }

}