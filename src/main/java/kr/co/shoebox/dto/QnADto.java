package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.shoebox.constant.QnASecret;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class QnADto {
    private Long id;

    private String title;

    private String question;

    private String answer;

    private String createdBy;

    private Long itemId;

    private QnASecret qnASecret;

    private String regTime;


    @QueryProjection
    public QnADto(Long id, String title, String question, String answer, String createdBy, Long itemId, QnASecret qnASecret, LocalDateTime regTime){
        this.id = id;
        this.title = title;
        this.question = question;
        this.answer = answer;
        this.createdBy = createdBy.substring(0,3)+"*******";
        this.itemId = itemId;
        this.qnASecret = qnASecret;
        this.regTime = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
