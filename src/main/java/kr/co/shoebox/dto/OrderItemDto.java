package kr.co.shoebox.dto;

import kr.co.shoebox.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {

    public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

    private String itemNm; //상품명

    private int size220Count; //220사이즈 수량
    private int size225Count; //225사이즈 수량
    private int size230Count; //230사이즈 수량
    private int size235Count; //235사이즈 수량
    private int size240Count; //240사이즈 수량
    private int size245Count; //245사이즈 수량
    private int size250Count; //250사이즈 수량
    private int size255Count; //255사이즈 수량
    private int size260Count; //260사이즈 수량
    private int size265Count; //265사이즈 수량
    private int size270Count; //270사이즈 수량
    private int size275Count; //275사이즈 수량
    private int size280Count; //280사이즈 수량
    private int size285Count; //285사이즈 수량
    private int size290Count; //290사이즈 수량
    private int size295Count; //295사이즈 수량
    private int size300Count; //300사이즈 수량

    private int count = size220Count + size225Count + size230Count + size235Count + size240Count + size245Count + size250Count + size255Count + size260Count + size265Count + size270Count + size275Count + size280Count + size285Count + size290Count + size295Count + size300Count; //총 주문수량

    private int orderPrice; //주문 금액
    private String imgUrl; //상품 이미지 경로

}