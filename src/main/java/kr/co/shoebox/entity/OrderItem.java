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

    private int orderPrice; //주문한 상품 가격

    private int count;

    private String size;

    private Boolean reviewStatus;

    private Boolean orderStatus;

    public static OrderItem createOrderItem(Item item, String size, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setSize(size);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());
        orderItem.setReviewStatus(false);
        item.removeStock(size, count);
        return orderItem;
    }

    public int getTotalPrice(){
        return orderPrice*count;
    }

    public void cancel() {
        this.getItem().addStock(size,count);
    }

}