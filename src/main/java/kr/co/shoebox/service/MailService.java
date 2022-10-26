package kr.co.shoebox.service;

import kr.co.shoebox.dto.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;

    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        mailSender.send(message);
    }

    public void tmpPWMailSend(String email, String pw) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("shoebox 임시 비밀번호입니다.");
        message.setText("안녕하세요. shoebox 임시비밀번호 입니다.\n임시비밀번호: " + pw);
        mailSender.send(message);
    }
}
