package kr.co.shoebox.controller;

import kr.co.shoebox.dto.MailDto;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    private final MemberRepository memberRepository;

    @PostMapping("/mail")
    public @ResponseBody ResponseEntity execMail(@RequestBody MailDto mailDto) {
        try{
            if(memberRepository.findByEmail(mailDto.getAddress())!=null){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            mailService.mailSend(mailDto);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}