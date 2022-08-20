package kr.co.shoebox.service;

import kr.co.shoebox.dto.*;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.entity.Question;
import kr.co.shoebox.entity.ReviewItem;
import kr.co.shoebox.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    private final QuestionRepository questionRepository;


    public int getQuestionCount(Long itemId){
        int qCount = 0;
        qCount = questionRepository.findQuestionCount(itemId);
        return qCount;
    }

    @Transactional(readOnly = true)
    public List<QuestionDetailDto> getQuestionItemList(Long itemId){

        List<QuestionDetailDto> questionDetailDtoList;

        questionDetailDtoList = questionRepository.findQuestionItemList(itemId);
        return questionDetailDtoList;
    }

    public List<QuestionMngDto> getQuestionMngList(){
        List<QuestionMngDto> questionMngDtoList;
        questionMngDtoList = questionRepository.getQuestionList();
        return questionMngDtoList;
    }

}