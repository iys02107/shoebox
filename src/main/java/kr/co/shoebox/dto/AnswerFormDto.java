package kr.co.shoebox.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;


@Getter @Setter
public class AnswerFormDto {

    @NotBlank(message = "답변은 필수 입력 값입니다.")
    private String answer;

    private Long id;

}