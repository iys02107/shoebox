package kr.co.shoebox.repository;


import kr.co.shoebox.dto.QuestionDetailDto;
import kr.co.shoebox.dto.QuestionMngDto;
import kr.co.shoebox.entity.Question;
import kr.co.shoebox.entity.ReviewItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select new kr.co.shoebox.dto.QuestionDetailDto(q.id, q.title, q.content, m.email, q.regTime, (select a.id from Answer a where a.questionId = q.id)) " +
            "from Question q " +
            "join q.member m " +
            "where q.itemId = :itemId " +
            "order by q.regTime desc")
    List<QuestionDetailDto> findQuestionItemList(Long itemId);

    @Query("select count(*) " +
            "from Question q " +
            "where q.itemId = :itemId")
    int findQuestionCount(Long itemId);


    @Query("select new kr.co.shoebox.dto.QuestionMngDto(q.id, i.itemNm, q.title, q.content, m.email, q.regTime, (select a.id from Answer a where a.questionId = q.id)) " +
            "from Question q, Item i " +
            "join q.member m " +
            "where q.itemId = i.id " +
            "order by q.regTime desc")
    List<QuestionMngDto> getQuestionList();

}