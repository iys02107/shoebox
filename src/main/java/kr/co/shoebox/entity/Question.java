package kr.co.shoebox.entity;

import kr.co.shoebox.dto.QuestionFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Optional;


@Entity
@Table(name = "question")
@Getter @Setter
@ToString
public class Question extends BaseEntity {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    private Long itemId;

    private String title;

    private String content;

    public static Question createQuestion(QuestionFormDto questionFormDto, Member member) {
        Question question = new Question();
        question.setMember(member);
        question.setItemId(questionFormDto.getItemId());
        question.setTitle(questionFormDto.getTitle());
        question.setContent(questionFormDto.getContent());
        return question;
    }


}