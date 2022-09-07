package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class AnswerDto {

    private Long id;

    private String content;

    private String regTime;

    private Long questionId;


@QueryProjection
    public AnswerDto(Long id, String content, LocalDateTime regTime, Long questionId){
        this.id = id;
        this.content = content;
        this.regTime = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.questionId = questionId;
    }

}