package kr.co.shoebox.service;

import kr.co.shoebox.constant.ItemSellStatus;
import kr.co.shoebox.constant.OrderStatus;
import kr.co.shoebox.dto.OrderDto;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.entity.Order;
import kr.co.shoebox.entity.OrderItem;
import kr.co.shoebox.repository.ItemRepository;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

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
    @DisplayName("주문 테스트")
    public void order(){
        Item item = saveItem();
        Member member = saveMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());

        Long orderId = orderService.order(orderDto, member.getEmail());
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount()*item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }

    @Test
    @DisplayName("주문 취소 테스트")
    public void cancelOrder(){
        Item item = saveItem();
        Member member = saveMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());
        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        orderService.cancelOrder(orderId);

        assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
        assertEquals(100, item.getSize220());
        assertEquals(100, item.getSize225());
        assertEquals(100, item.getSize230());
        assertEquals(100, item.getSize235());
        assertEquals(100, item.getSize240());
        assertEquals(100, item.getSize245());
        assertEquals(100, item.getSize250());
        assertEquals(100, item.getSize255());
        assertEquals(100, item.getSize260());
        assertEquals(100, item.getSize265());
        assertEquals(100, item.getSize270());
        assertEquals(100, item.getSize275());
        assertEquals(100, item.getSize280());
        assertEquals(100, item.getSize285());
        assertEquals(100, item.getSize290());
        assertEquals(100, item.getSize295());
        assertEquals(100, item.getSize300());
    }

}