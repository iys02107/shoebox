package kr.co.shoebox.controller;

import kr.co.shoebox.constant.QnASecret;
import kr.co.shoebox.constant.Role;
import kr.co.shoebox.dto.AnswerFormDto;
import kr.co.shoebox.dto.QnADto;
import kr.co.shoebox.dto.QuestionFormDto;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.entity.QnA;
import kr.co.shoebox.repository.ItemRepository;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.QnARepository;
import kr.co.shoebox.service.QnAService;
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
@RequestMapping("/qna")
public class QnAController {

    private final MemberRepository memberRepository;

    private final QnARepository qnARepository;

    private final ItemRepository itemRepository;

    private final QnAService qnAService;

    @GetMapping(value = "/question/new/{itemId}")
    public String questionForm(Model model, @PathVariable("itemId") Long itemId){
        model.addAttribute("questionFormDto", new QuestionFormDto());
        model.addAttribute("itemId", itemId);
        return "qna/questionForm";
    }

    @PostMapping(value = "/question/new")
    public String newQuestion(@Valid QuestionFormDto questionFormDto, @RequestParam("itemId") Long itemId, Principal principal){
        questionFormDto.setItemId(itemId);
        Member member = memberRepository.findByEmail(principal.getName());
        Item item = itemRepository.getById(itemId);
        QnA question= QnA.createQuestion(questionFormDto, item);
        qnARepository.save(question);
        return "qna/questionAlert";
    }

    @GetMapping(value = "/answer/new/{id}")
    public String answerForm(Model model, @PathVariable("id") Long id){
        QnA qnA = qnARepository.getById(id);
        model.addAttribute("qna",qnA);
        model.addAttribute("id", id);
        return "qna/AnswerForm";
    }

    @PostMapping(value = "/answer/new")
    public String newAnswer(@RequestParam("answer") String answer, @RequestParam("questionId") Long id){
        QnA qnA = qnARepository.getById(id);
        qnA.setAnswer(answer);
        qnARepository.save(qnA);
        return "qna/AnswerAlert";
    }

    @GetMapping(value = "/qnaMng")
    public String qnaMng(Model model){
        List<QnA> qnAList = qnARepository.findAll();
        model.addAttribute("qnAList", qnAList);
        return "qna/qnaMng";
    }

    @GetMapping(value = "/qnaList")
    public String qnaList(Model model, Principal principal){
        List<QnA> qnAList = qnARepository.findQnAByCreatedBy(principal.getName());
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        model.addAttribute("qnAList",qnAList);
        return "qna/qnaList";
    }

    @PostMapping(value = "/delete/{qnAId}")
    public @ResponseBody ResponseEntity qnaDelete(@PathVariable("qnAId") Long qnAId , Principal principal){

        if(!qnAService.validateQnA(qnAId, principal.getName())&&memberRepository.findByEmail(principal.getName()).getRole().equals(Role.USER)){
            return new ResponseEntity<String>("문의 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        qnARepository.deleteById(qnAId);
        return new ResponseEntity<Long>(qnAId, HttpStatus.OK);
    }

    @GetMapping(value = "/update/{qnAId}")
    public String questionUpdateForm(Model model, @PathVariable("qnAId") Long qnAId){
        QnA qnA = qnARepository.getById(qnAId);
        model.addAttribute("qna",qnA);
        return "qna/questionUpdateForm";
    }

    @PostMapping(value = "/update")
    public String newAnswer(@RequestParam("question") String question, @RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("qnASecret") QnASecret qnASecret){
        QnA qnA = qnARepository.getById(id);
        qnA.setTitle(title);
        qnA.setQuestion(question);
        qnA.setQnASecret(qnASecret);
        qnARepository.save(qnA);
        return "qna/questionAlert";
    }

    @GetMapping(value = "/answer/update/{qnAId}")
    public String answerUpdateForm(Model model, @PathVariable("qnAId") Long qnAId){
        QnA qnA = qnARepository.getById(qnAId);
        model.addAttribute("qna",qnA);
        return "qna/answerUpdateForm";
    }

    @PostMapping(value = "/answer/update")
    public String answerUpdate(@RequestParam("qnAId") Long qnAId, @RequestParam("answer") String answer){
        QnA qnA = qnARepository.getById(qnAId);
        qnA.setAnswer(answer);
        qnARepository.save(qnA);
        return "qna/answerAlert";
    }

}
