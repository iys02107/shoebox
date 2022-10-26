package kr.co.shoebox.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FindPWDto {
    private String email;
    private int question;
    private String answer;
}
