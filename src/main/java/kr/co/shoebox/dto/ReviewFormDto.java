package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ReviewFormDto {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "리뷰 내용은 필수 입력 값입니다.")
    @Length(min= 5, message = "리뷰 내용은 5자 이상 작성하세요.")
    private String content;

    @NotBlank(message = "별점은 필수 입력 값입니다.")
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