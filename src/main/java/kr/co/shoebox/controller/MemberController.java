package kr.co.shoebox.controller;

import kr.co.shoebox.dto.MemberFormDto;
import kr.co.shoebox.dto.MemberUpdateDto;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;


@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()||memberService.passwordConfirm(memberFormDto,bindingResult)){
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/myPage")
    public String userName(Model model, Principal principal){
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        return "member/myPage";
    }


    @PostMapping(value = "/pwCheck")
    public String pwCheck(@RequestParam("password") String pw, Principal principal, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        if(!encoder.matches(pw, member.getPassword())) {
            model.addAttribute("pwCheckErrorMsg", "비밀번호가 일치하지 않습니다");
            return "member/pwCheckForm";
        } else {

            return "member/pwUpdateForm";
        }

    }

    @GetMapping("/pwCheckForm")
    public String pwCheckForm(Principal principal, Model model) {
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        return "member/pwCheckForm";
    }

    @PostMapping("/pwUpdate")
    public String pwUpdate(Principal principal, Model model, @RequestParam("newPassword") String pw) {
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        member.setPassword(passwordEncoder.encode(pw));
        memberRepository.save(member);
        return "member/myPage";
    }

    @GetMapping("/memberDeleteForm")
    public String memberDelete(Principal principal, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        return "member/memberDeleteForm";
    }

    @PostMapping("/memberDelete")
    public String memberDeleteForm(@RequestParam("password") String pw, Principal principal, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        if(!encoder.matches(pw, member.getPassword())) {
            model.addAttribute("pwCheckErrorMsg", "비밀번호가 일치하지 않습니다");
            return "member/memberDeleteForm";
        } else {
            memberRepository.delete(member);
            SecurityContextHolder.clearContext();
            return "redirect:/";
        }
    }

    @GetMapping("/memberUpdateForm")
    public String memberUpdateForm(Principal principal, Model model) {
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        model.addAttribute("email", member.getEmail());
        model.addAttribute("postcode", member.getPostcode());
        model.addAttribute("roadAddress", member.getRoadAddress());
        model.addAttribute("detailAddress", member.getDetailAddress());
        model.addAttribute("phoneNumber", member.getPhoneNumber());
        return "member/memberUpdateForm";
    }


    @PostMapping("/memberUpdate")
    public String memberUpdate(MemberUpdateDto memberUpdateDto, Principal principal) {
        Member member = memberRepository.findByEmail(principal.getName());
        member.setPostcode(memberUpdateDto.getPostcode());
        member.setRoadAddress(memberUpdateDto.getRoadAddress());
        member.setDetailAddress(memberUpdateDto.getDetailAddress());
        member.setPhoneNumber(memberUpdateDto.getPhoneNumber());
        memberRepository.save(member);
        return "member/myPage";
    }


}