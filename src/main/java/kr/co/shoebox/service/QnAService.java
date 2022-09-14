package kr.co.shoebox.service;

import kr.co.shoebox.dto.QnADto;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.repository.ItemRepository;
import kr.co.shoebox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.co.shoebox.repository.QnARepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QnAService {

    private final QnARepository qnARepository;

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public int getQnACount(Long itemId){
        int qCount = 0;
        qCount = qnARepository.findQnACount(itemId);
        return qCount;
    }
}