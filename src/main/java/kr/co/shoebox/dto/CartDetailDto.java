package kr.co.shoebox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDto {

    private Long cartItemId; //장바구니 상품 아이디

    private String itemNm; //상품명

    private int price; //상품 금액

    private int count; //주문수량

    private String size; //상품 사이즈

    private String imgUrl; //상품 이미지 경로

    private Long itemId;

    public CartDetailDto(Long cartItemId, String itemNm, int price, int count, String size, String imgUrl, Long itemId){
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.size = size;
        this.imgUrl = imgUrl;
        this.itemId = itemId;
    }

}