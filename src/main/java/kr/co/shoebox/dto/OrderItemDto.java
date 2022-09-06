package kr.co.shoebox.dto;

import kr.co.shoebox.entity.OrderItem;
import kr.co.shoebox.entity.ReviewItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {

    public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.itemId = orderItem.getItem().getId();
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.size = orderItem.getSize();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
        this.orderItemId = orderItem.getId();
        this.reviewStatus = orderItem.getReviewStatus();
    }

    private Long itemId;

    private String itemNm; //상품명

    private int count; //주문 수량

    private String size; //주문 사이즈

    private int orderPrice; //주문 금액
    private String imgUrl; //상품 이미지 경로

    private Long orderItemId;

    private Boolean reviewStatus;

}