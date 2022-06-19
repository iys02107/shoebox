package kr.co.shoebox.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message="비밀번호를 한번 더 입력하세요")
    private String passwordCheck;

    @NotBlank(message = "우편번호는 필수 입력 값입니다.")
    private String postcode;

    @NotEmpty(message = "도로명 주소는 필수 입력 값입니다.")
    private String roadAddress;

    @NotEmpty(message = "상세주소는 필수 입력 값입니다.")
    private String detailAddress;

    @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
    @Length(min= 10, max=11, message = "올바른 전화번호를 입력하세요")
    private String phoneNumber;
}