package kr.co.shoebox.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="wish_item")
public class WishItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "wish_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wish_id")
    private Wish wish;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    public static WishItem createWishItem(Wish wish, Item item) {
        WishItem wishItem = new WishItem();
        wishItem.setWish(wish);
        wishItem.setItem(item);
        return wishItem;
    }
}