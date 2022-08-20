package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.entity.ReviewItem;
import kr.co.shoebox.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class ReviewItemDto {

    private Long id;

    private String content;

    private int rate;

    private Long memberId;

    private String regTime;

    private String email;

    private String size;

    @QueryProjection
    public ReviewItemDto(Long id, String content, int rate, Long memberId, LocalDateTime regTime, String size){
        this.id = id;
        this.content = content;
        this.rate = rate;
        this.memberId = memberId;
        this.regTime = regTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.size = size;
    }

}