package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class WishItemDto {

    private Long wishItemId;

    private String brand;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    private Long itemId;

    private List<WishItemDto> wishItemDtoList;

    @QueryProjection
    public WishItemDto(Long wishItemId, String itemNm, String itemDetail, String imgUrl, Integer price, Long itemId){
        this.wishItemId = wishItemId;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
        this.itemId = itemId;
    }

}