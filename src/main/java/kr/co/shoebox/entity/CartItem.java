package kr.co.shoebox.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="cart_item")
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count; //주문수량;

    private String size;

    public static CartItem createCartItem(Cart cart, Item item, int count , String size) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        cartItem.setSize(size);
        return cartItem;
    }

    public void addCount(String size, int count){
        this.size = size;
        this.count += count;
    }

    public void updateCount(String size, int count){
        this.size = size;
        this.count = count;
    }

}