package kr.co.shoebox.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class OrderDto {

    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;

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

    private int total = size220Count + size225Count + size230Count + size235Count + size240Count + size245Count + size250Count + size255Count + size260Count + size265Count + size270Count + size275Count + size280Count + size285Count + size290Count + size295Count + size300Count; //총 주문수량

    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
    private int count = total;
}