package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {

    private Long id;

    private String brand;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainItemDto(Long id, String brand, String itemNm, String itemDetail, String imgUrl,Integer price){
        this.id = id;
        this.brand = brand;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }

}