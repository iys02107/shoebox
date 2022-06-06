package kr.co.shoebox.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격

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

    public static OrderItem createOrderItem(Item item, int count, int size220Count, int size225Count, int size230Count, int size235Count, int size240Count, int size245Count, int size250Count, int size255Count, int size260Count, int size265Count, int size270Count, int size275Count, int size280Count, int size285Count, int size290Count, int size295Count, int size300Count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());
        item.removeStock_220(size220Count);
        item.removeStock_225(size225Count);
        item.removeStock_230(size230Count);
        item.removeStock_235(size235Count);
        item.removeStock_240(size240Count);
        item.removeStock_245(size245Count);
        item.removeStock_250(size250Count);
        item.removeStock_255(size255Count);
        item.removeStock_260(size260Count);
        item.removeStock_265(size265Count);
        item.removeStock_270(size270Count);
        item.removeStock_275(size275Count);
        item.removeStock_280(size280Count);
        item.removeStock_285(size285Count);
        item.removeStock_290(size290Count);
        item.removeStock_295(size295Count);
        item.removeStock_300(size300Count);
        return orderItem;
    }

    public int getTotalPrice(){
        return orderPrice*count;
    }

    public void cancel() {
        this.getItem().addStock_220(size220Count);
        this.getItem().addStock_225(size225Count);
        this.getItem().addStock_230(size230Count);
        this.getItem().addStock_235(size235Count);
        this.getItem().addStock_240(size240Count);
        this.getItem().addStock_245(size245Count);
        this.getItem().addStock_250(size250Count);
        this.getItem().addStock_255(size255Count);
        this.getItem().addStock_260(size260Count);
        this.getItem().addStock_265(size265Count);
        this.getItem().addStock_270(size270Count);
        this.getItem().addStock_275(size275Count);
        this.getItem().addStock_280(size280Count);
        this.getItem().addStock_285(size285Count);
        this.getItem().addStock_290(size290Count);
        this.getItem().addStock_295(size295Count);
        this.getItem().addStock_300(size300Count);
    }

}