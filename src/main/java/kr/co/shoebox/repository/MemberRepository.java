package kr.co.shoebox.repository;

import kr.co.shoebox.entity.CartItem;
import kr.co.shoebox.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member getById(Long memberId);
}