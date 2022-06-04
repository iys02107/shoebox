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

    public void removeStock_220(int stockNumber){
        int restStock = this.size220 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("220사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size220 + ")");
        }
        this.size220 = restStock;
    }

    public void addStock_220(int stockNumber){
        this.size220 += stockNumber;
    }


    public void removeStock_225(int stockNumber){
        int restStock = this.size225 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("225사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size225 + ")");
        }
        this.size225 = restStock;
    }

    public void addStock_225(int stockNumber){
        this.size225 += stockNumber;
    }

    public void removeStock_230(int stockNumber){
        int restStock = this.size230 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("230사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size230 + ")");
        }
        this.size230 = restStock;
    }

    public void addStock_230(int stockNumber){
        this.size230 += stockNumber;
    }


    public void removeStock_235(int stockNumber){
        int restStock = this.size235 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("235사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size235 + ")");
        }
        this.size235 = restStock;
    }

    public void addStock_235(int stockNumber){
        this.size235 += stockNumber;
    }

    public void removeStock_240(int stockNumber){
        int restStock = this.size240 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("240사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size240 + ")");
        }
        this.size240 = restStock;
    }

    public void addStock_240(int stockNumber){
        this.size240 += stockNumber;
    }

    public void removeStock_245(int stockNumber){
        int restStock = this.size245 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("245사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size245 + ")");
        }
        this.size245 = restStock;
    }

    public void addStock_245(int stockNumber){
        this.size245 += stockNumber;
    }


    public void removeStock_250(int stockNumber){
        int restStock = this.size250 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("250사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size250 + ")");
        }
        this.size250 = restStock;
    }

    public void addStock_250(int stockNumber){
        this.size250 += stockNumber;
    }

    public void removeStock_255(int stockNumber){
        int restStock = this.size255 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("255사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size255 + ")");
        }
        this.size255 = restStock;
    }

    public void addStock_255(int stockNumber){
        this.size255 += stockNumber;
    }

    public void removeStock_260(int stockNumber){
        int restStock = this.size260 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("260사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size260 + ")");
        }
        this.size260 = restStock;
    }

    public void addStock_260(int stockNumber){
        this.size260 += stockNumber;
    }

    public void removeStock_265(int stockNumber){
        int restStock = this.size265 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("265사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size265 + ")");
        }
        this.size265 = restStock;
    }

    public void addStock_265(int stockNumber){
        this.size265 += stockNumber;
    }


    public void removeStock_270(int stockNumber){
        int restStock = this.size270 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("270사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size270 + ")");
        }
        this.size270 = restStock;
    }

    public void addStock_270(int stockNumber){
        this.size270 += stockNumber;
    }

    public void removeStock_275(int stockNumber){
        int restStock = this.size275 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("275사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size275 + ")");
        }
        this.size275 = restStock;
    }

    public void addStock_275(int stockNumber){
        this.size275 += stockNumber;
    }

    public void removeStock_280(int stockNumber){
        int restStock = this.size280 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("280사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size280 + ")");
        }
        this.size280 = restStock;
    }

    public void addStock_280(int stockNumber){
        this.size280 += stockNumber;
    }

    public void removeStock_285(int stockNumber){
        int restStock = this.size285 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("285사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size285 + ")");
        }
        this.size285 = restStock;
    }

    public void addStock_285(int stockNumber){
        this.size285 += stockNumber;
    }

    public void removeStock_290(int stockNumber){
        int restStock = this.size290 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("290사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size290 + ")");
        }
        this.size290 = restStock;
    }

    public void addStock_290(int stockNumber){
        this.size290 += stockNumber;
    }

    public void removeStock_295(int stockNumber){
        int restStock = this.size295 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("295사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size295 + ")");
        }
        this.size295 = restStock;
    }

    public void addStock_295(int stockNumber){
        this.size295 += stockNumber;
    }

    public void removeStock_300(int stockNumber){
        int restStock = this.size300 - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("300사이즈의 재고가 부족 합니다. (현재 재고 수량: " + this.size300 + ")");
        }
        this.size220 = restStock;
    }

    public void addStock_300(int stockNumber){
        this.size300 += stockNumber;
    }

}