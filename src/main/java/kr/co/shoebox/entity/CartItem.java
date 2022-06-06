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

    private int count = size220Count + size225Count + size230Count + size235Count + size240Count + size245Count + size250Count + size255Count + size260Count + size265Count + size270Count + size275Count + size280Count + size285Count + size290Count + size295Count + size300Count; //총 주문수량;

    public static CartItem createCartItem(Cart cart, Item item, int count, int size220Count, int size225Count, int size230Count, int size235Count, int size240Count, int size245Count, int size250Count, int size255Count, int size260Count, int size265Count, int size270Count, int size275Count, int size280Count, int size285Count, int size290Count, int size295Count, int size300Count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        cartItem.setSize220Count(size220Count);
        cartItem.setSize225Count(size225Count);
        cartItem.setSize230Count(size230Count);
        cartItem.setSize235Count(size235Count);
        cartItem.setSize240Count(size240Count);
        cartItem.setSize245Count(size245Count);
        cartItem.setSize250Count(size250Count);
        cartItem.setSize255Count(size255Count);
        cartItem.setSize260Count(size260Count);
        cartItem.setSize265Count(size265Count);
        cartItem.setSize270Count(size270Count);
        cartItem.setSize275Count(size275Count);
        cartItem.setSize280Count(size280Count);
        cartItem.setSize285Count(size285Count);
        cartItem.setSize290Count(size290Count);
        cartItem.setSize295Count(size295Count);
        cartItem.setSize300Count(size300Count);
        return cartItem;
    }

    public void addCount(int count){
        this.count += count;
    }

    public void updateCount(int count){
        this.count = count;
    }

}