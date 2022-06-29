package kr.co.shoebox.repository;

import kr.co.shoebox.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;



public interface WishRepository extends JpaRepository<Wish, Long> {

    Wish findByMemberId(Long memberId);

}