package kr.co.shoebox.entity;

import kr.co.shoebox.constant.ItemSellStatus;
import kr.co.shoebox.repository.ItemRepository;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.OrderItemRepository;
import kr.co.shoebox.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@Transactional
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public Item createItem(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setBrand("나이키");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
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
        item.setRegTime(LocalDateTime.now());

        item.setUpdateTime(LocalDateTime.now());
        return item;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest() {

        Order order = new Order();

        for(int i=0;i<3;i++){
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3, savedOrder.getOrderItems().size());
    }

    public Order createOrder(){
        Order order = new Order();
        for(int i=0;i<3;i++){
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(orderItem.getCount());
            orderItem.setSize220Count(10);
            orderItem.setSize225Count(10);
            orderItem.setSize230Count(10);
            orderItem.setSize235Count(10);
            orderItem.setSize240Count(10);
            orderItem.setSize245Count(10);
            orderItem.setSize250Count(10);
            orderItem.setSize255Count(10);
            orderItem.setSize260Count(10);
            orderItem.setSize265Count(10);
            orderItem.setSize270Count(10);
            orderItem.setSize275Count(10);
            orderItem.setSize280Count(10);
            orderItem.setSize285Count(10);
            orderItem.setSize290Count(10);
            orderItem.setSize295Count(10);
            orderItem.setSize300Count(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        Member member = new Member();
        memberRepository.save(member);
        order.setMember(member);
        orderRepository.save(order);
        return order;
    }

    @Test
    @DisplayName("고아객체 제거 테스트")
    public void orphanRemovalTest(){
        Order order = this.createOrder();
        order.getOrderItems().remove(0);
        em.flush();
    }

    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest(){
        Order order = this.createOrder();
        Long orderItemId = order.getOrderItems().get(0).getId();
        em.flush();
        em.clear();
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(EntityNotFoundException::new);
        System.out.println("Order class : " + orderItem.getOrder().getClass());
        System.out.println("===========================");
        orderItem.getOrder().getOrderDate();
        System.out.println("===========================");
    }

}