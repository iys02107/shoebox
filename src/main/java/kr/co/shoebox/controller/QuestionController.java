package kr.co.shoebox.controller;


import kr.co.shoebox.dto.QuestionFormDto;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.entity.Question;
import kr.co.shoebox.entity.ReviewItem;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.QuestionRepository;
import kr.co.shoebox.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/questions")

public class QuestionController {

    private final QuestionService questionService;

    private final MemberRepository memberRepository;

    private final QuestionRepository questionRepository;

    @GetMapping(value = "/new/{itemId}")
    public String questionForm(Model model,  @PathVariable("itemId") Long itemId){
        model.addAttribute("questionFormDto", new QuestionFormDto());
        model.addAttribute("itemId", itemId);
        return "question/questionForm";
    }

    @PostMapping(value = "/new")
    public String newQuestion(@Valid QuestionFormDto questionFormDto, @RequestParam("itemId") Long itemId, Principal principal, Model model){

        try {
            questionFormDto.setItemId(itemId);

            Member member = memberRepository.findByEmail(principal.getName());
            Question question = Question.createQuestion(questionFormDto, member);
            questionRepository.save(question);

        } catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "question/questionForm";
        }

        return "question/questionAlert";
    }


}