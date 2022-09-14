package kr.co.shoebox.entity;

import kr.co.shoebox.constant.QnASecret;
import kr.co.shoebox.dto.QuestionFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "qna")
@Getter
@Setter
@ToString
public class QnA extends BaseEntity {

    @Id
    @Column(name = "qna_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String question;

    private String answer;

    @Enumerated(EnumType.STRING)
    private QnASecret qnASecret;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_id")
    private Item item;

    public static QnA createQuestion(QuestionFormDto questionFormDto, Item item){
        QnA qna = new QnA();
        qna.setTitle(questionFormDto.getTitle());
        qna.setQnASecret(questionFormDto.getQnASecret());
        qna.setQuestion(questionFormDto.getQuestion());
        qna.setItem(item);
        return qna;
    }
}
