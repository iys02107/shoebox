package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewFormDto {

    private String title;

    private String content;

    private String rate;

//    @QueryProjection
//    public ReviewFormDto(Long reviewId, String title, String content, String reviewImgUrl, int rate, Item item){
//        this.reviewId = reviewId;
//        this.title = title;
//        this.content = content;
//        this.reviewImgUrl = reviewImgUrl;
//        this.rate = rate;
//        this.item = item;
//    }

}