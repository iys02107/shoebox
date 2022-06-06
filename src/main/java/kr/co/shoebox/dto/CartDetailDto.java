package kr.co.shoebox.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDto {

    private Long cartItemId; //장바구니 상품 아이디

    private String itemNm; //상품명

    private int price; //상품 금액

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

    private String imgUrl; //상품 이미지 경로

    public CartDetailDto(Long cartItemId, String itemNm, int price, int count, int size220Count, int size225Count, int size230Count, int size235Count, int size240Count, int size245Count, int size250Count, int size255Count, int size260Count, int size265Count, int size270Count, int size275Count, int size280Count, int size285Count, int size290Count, int size295Count, int size300Count, String imgUrl){
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.size220Count = size220Count;
        this.size225Count = size225Count;
        this.size230Count = size235Count;
        this.size240Count = size240Count;
        this.size245Count = size245Count;
        this.size250Count = size250Count;
        this.size255Count = size255Count;
        this.size260Count = size260Count;
        this.size265Count = size265Count;
        this.size270Count = size270Count;
        this.size275Count = size275Count;
        this.size280Count = size280Count;
        this.size285Count = size285Count;
        this.size290Count = size290Count;
        this.size295Count = size295Count;
        this.size300Count = size300Count;
        this.imgUrl = imgUrl;
    }

}