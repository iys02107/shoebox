package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.shoebox.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDetailDto {

    private Long id;

    private String title;

    private String content;

    private String rate;

    private Long itemId;

    private String imgUrl;

    private String itemNm;



    @QueryProjection
    public ReviewDetailDto(Long id, String title, String content, String rate, Long itemId, String imgUrl, String itemNm){
        this.id = id;
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.itemId = itemId;
        this.imgUrl = imgUrl;
        this.itemNm = itemNm;
    }

}