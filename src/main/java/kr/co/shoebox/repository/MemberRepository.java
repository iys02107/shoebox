package kr.co.shoebox.repository;

import kr.co.shoebox.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member getById(Long memberId);

    @Query("select m.email from Member m where m.name = :name and m.phoneNumber = :phoneNumber")
    String findEmail(String name, String phoneNumber);

    Member findByPhoneNumber(String phoneNumber);

    @Query("select m.id from Member m where m.email = :email and m.question = :question and m.answer = :answer")
    Long findPW(String email, int question, String answer);


}