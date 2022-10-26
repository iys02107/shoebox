package kr.co.shoebox.service;


import kr.co.shoebox.dto.MemberFormDto;

import kr.co.shoebox.entity.Member;
import kr.co.shoebox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;



    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        Member findMember1 = memberRepository.findByPhoneNumber(member.getPhoneNumber());
        if(findMember != null){
            throw new IllegalStateException("해당 이메일은 이미 사용중입니다.");
        }
        if(findMember1 != null){
            throw new IllegalStateException("입력하신 전화번호는 이미 가입되어 있습니다.");
        }
    }
    public boolean passwordConfirm(MemberFormDto memberFormDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return true;

        if(!memberFormDto.getPassword().equals(memberFormDto.getPasswordCheck())){
            bindingResult.rejectValue("passwordConfirm",null,"비밀번호가 일치하지 않습니다.");
            return true;
        }
        return false;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    public String tmpPW(String email){
        Member member = memberRepository.findByEmail(email);
        String pw = "";
        UUID uid = UUID.randomUUID();
        pw = uid.toString().substring(0,6);
        return pw;
    }

}