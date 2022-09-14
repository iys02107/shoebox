package kr.co.shoebox.controller;

import kr.co.shoebox.dto.AnswerFormDto;
import kr.co.shoebox.dto.QuestionFormDto;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.entity.QnA;
import kr.co.shoebox.repository.ItemRepository;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.QnARepository;
import lombok.RequiredArgsConstructor;
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
}
