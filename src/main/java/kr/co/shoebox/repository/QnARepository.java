package kr.co.shoebox.repository;

import kr.co.shoebox.dto.QnADto;
import kr.co.shoebox.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {

    List<QnADto> findQnAByItemId(Long itemId);

    List<QnA> findQnAByCreatedBy(String CreatedBy);

}
