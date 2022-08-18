package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.OrderItem;
import kr.co.shoebox.entity.ReviewItem;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ReviewFormDto {

    @NotBlank(message = "리뷰 내용은 필수 입력 값입니다.")
    @Length(min= 5, max= 200, message = "리뷰 내용은 5자 이상 200자 이하로 작성하세요.")
    private String content;

    @NotNull(message = "별점은 필수 입력 값입니다.")
    private int rate;

}