package kr.co.shoebox.service;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.entity.OrderItem;
import kr.co.shoebox.entity.QnA;
import kr.co.shoebox.entity.ReviewItem;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
@Transactional
public class QnAService {

    private final QnARepository qnARepository;

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public boolean validateQnA(Long qnAId, String email){
        Member curMember = memberRepository.findByEmail(email);
        QnA qnA = qnARepository.findById(qnAId)
                .orElseThrow(EntityNotFoundException::new);
        String savedMember = qnA.getCreatedBy();

        if(!StringUtils.equals(curMember.getEmail(), savedMember)){
            return false;
        }
        return true;
    }

}