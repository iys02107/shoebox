package kr.co.shoebox.entity;

import kr.co.shoebox.constant.ItemSellStatus;
import kr.co.shoebox.dto.ItemFormDto;
import kr.co.shoebox.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;       //상품 코드

    @Column(nullable = false)
    private String brand; //브랜드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int size220; //220사이즈 재고수량

    @Column(nullable = false)
    private int size225; //225사이즈 재고수량

    @Column(nullable = false)
    private int size230; //230사이즈 재고수량

    @Column(nullable = false)
    private int size235; //235사이즈 재고수량

    @Column(nullable = false)
    private int size240; //240사이즈 재고수량

    @Column(nullable = false)
    private int size245; //245사이즈 재고수량

    @Column(nullable = false)
    private int size250; //250사이즈 재고수량

    @Column(nullable = false)
    private int size255; //255사이즈 재고수량

    @Column(nullable = false)
    private int size260; //260사이즈 재고수량

    @Column(nullable = false)
    private int size265; //265사이즈 재고수량

    @Column(nullable = false)
    private int size270; //270사이즈 재고수량

    @Column(nullable = false)
    private int size275; //275사이즈 재고수량

    @Column(nullable = false)
    private int size280; //280사이즈 재고수량

    @Column(nullable = false)
    private int size285; //285사이즈 재고수량

    @Column(nullable = false)
    private int size290; //290사이즈 재고수량

    @Column(nullable = false)
    private int size295; //295사이즈 재고수량

    @Column(nullable = false)
    private int size300; //300사이즈 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.brand = itemFormDto.getBrand();
        this.price = itemFormDto.getPrice();
        this.size220 = itemFormDto.getSize220();
        this.size225 = itemFormDto.getSize225();
        this.size230 = itemFormDto.getSize230();
        this.size235 = itemFormDto.getSize235();
        this.size240 = itemFormDto.getSize240();
        this.size245 = itemFormDto.getSize245();
        this.size250 = itemFormDto.getSize250();
        this.size255 = itemFormDto.getSize255();
        this.size260 = itemFormDto.getSize260();
        this.size265 = itemFormDto.getSize265();
        this.size270 = itemFormDto.getSize270();
        this.size275 = itemFormDto.getSize275();
        this.size280 = itemFormDto.getSize280();
        this.size285 = itemFormDto.getSize285();
        this.size290 = itemFormDto.getSize290();
        this.size295 = itemFormDto.getSize295();
        this.size300 = itemFormDto.getSize300();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(String size, int count){
        size= "220";
        int restStock = this.size220 - count;
        if(restStock<0){
            throw new OutOfStockException("220사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size220 + ")");
        }
        this.size220 = restStock;
    }

    public void addStock(String size, int count){
        this.size220 += count;
    }


}