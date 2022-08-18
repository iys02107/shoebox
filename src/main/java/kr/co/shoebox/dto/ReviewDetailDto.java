package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.shoebox.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDetailDto {

    private Long id;

    private String content;

    private int rate;

    private Long itemId;

    private String imgUrl;

    private String itemNm;

    @QueryProjection
    public ReviewDetailDto(Long id, String content, int rate, Long itemId, String imgUrl, String itemNm){
        this.id = id;
        this.content = content;
        this.rate = rate;
        this.itemId = itemId;
        this.imgUrl = imgUrl;
        this.itemNm = itemNm;
    }

}