package kr.co.shoebox.repository;

import kr.co.shoebox.entity.Order;
import kr.co.shoebox.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select oi from OrderItem oi " +
            "where oi.id = :orderItemId")
    OrderItem findByOrderItemId(Long orderItemId);

    @Query("select oi from OrderItem oi join oi.order o where o.id = :orderId")
    OrderItem findOrderItemByOrderId(Long orderId);
}