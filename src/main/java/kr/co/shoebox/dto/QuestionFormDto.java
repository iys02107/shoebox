package kr.co.shoebox.dto;

import kr.co.shoebox.constant.QnASecret;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;


@Getter @Setter
public class QuestionFormDto {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "문의 내용은 필수 입력 값입니다.")
    @Length(min= 5, max = 200, message = "문의 내용은 5자 이상 200자 이하로 작성하세요.")
    private String question;

    private QnASecret qnASecret;

    private Long itemId;

}