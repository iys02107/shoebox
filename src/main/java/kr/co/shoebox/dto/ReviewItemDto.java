package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewItemDto {

    private Long id;

    private String title;

    private String content;

    private int rate;


    @QueryProjection
    public ReviewItemDto(Long id, String title, String content, int rate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.rate = rate;
    }

}