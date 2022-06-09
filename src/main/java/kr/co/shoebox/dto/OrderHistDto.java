package kr.co.shoebox.dto;

import kr.co.shoebox.constant.OrderStatus;
import kr.co.shoebox.entity.Order;
import kr.co.shoebox.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderHistDto {

    public OrderHistDto(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
        this.orderTotalPrice = order.getTotalPrice();
    }

    private Long orderId; //주문아이디
    private String orderDate; //주문날짜
    private OrderStatus orderStatus; //주문 상태

    private int orderTotalPrice; // 주문별 총 주문 금액

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    private List<OrderItem> orderItemList = new ArrayList<>();


    //주문 상품리스트
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }

}