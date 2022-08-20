package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class QuestionMngDto {

    private Long id;

    private String itemNm;

    private String title;

    private String content;

    private String email;

    private String regTime;

    private Long answerId;


@QueryProjection
    public QuestionMngDto(Long id, String itemNm, String title, String content, String email, LocalDateTime regTime, Long answerId){
        this.id = id;
        this.itemNm = itemNm;
        this.title = title;
        this.content = content;
        this.email = email;
        this.regTime = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.answerId = answerId;
    }

}