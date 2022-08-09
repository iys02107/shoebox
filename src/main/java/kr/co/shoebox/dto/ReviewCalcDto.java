package kr.co.shoebox.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;


@Getter @Setter
public class ReviewCalcDto {

    @ColumnDefault("0")
    private double rate;

    @ColumnDefault("0")
    private Long count;

    @QueryProjection
    public ReviewCalcDto(double rate, Long count){
        super();
        this.rate = rate;
        this.count = count;
    }

    public ReviewCalcDto(){

    }
}