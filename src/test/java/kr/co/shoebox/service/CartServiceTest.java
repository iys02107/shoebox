package kr.co.shoebox.service;

import kr.co.shoebox.constant.ItemSellStatus;
import kr.co.shoebox.dto.CartItemDto;
import kr.co.shoebox.entity.CartItem;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.repository.CartItemRepository;
import kr.co.shoebox.repository.ItemRepository;
import kr.co.shoebox.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class CartServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository cartItemRepository;

    public Item saveItem(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setBrand("나이키");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setSize220(100);
        item.setSize225(100);
        item.setSize230(100);
        item.setSize235(100);
        item.setSize240(100);
        item.setSize245(100);
        item.setSize250(100);
        item.setSize255(100);
        item.setSize260(100);
        item.setSize265(100);
        item.setSize270(100);
        item.setSize275(100);
        item.setSize280(100);
        item.setSize285(100);
        item.setSize290(100);
        item.setSize295(100);
        item.setSize300(100);

        return itemRepository.save(item);
    }

    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("장바구니 담기 테스트")
    public void addCart(){
        Item item = saveItem();
        Member member = saveMember();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCount(5);
        cartItemDto.setSize("size220");
        cartItemDto.setItemId(item.getId());

        Long cartItemId = cartService.addCart(cartItemDto, member.getEmail());
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(item.getId(), cartItem.getItem().getId());
        assertEquals(cartItemDto.getCount(), cartItem.getCount());
        assertEquals(cartItemDto.getSize(), cartItem.getSize());
    }

}