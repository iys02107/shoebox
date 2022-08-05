package kr.co.shoebox.entity;

import kr.co.shoebox.dto.ReviewFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "reviewItem")
@Getter @Setter
@ToString
public class ReviewItem extends BaseEntity {

    @Id
    @Column(name = "review_item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String rate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long orderItemId;

    public static ReviewItem createReviewItem(ReviewFormDto reviewFormDto, Member member, Long orderItemId){
        ReviewItem reviewItem = new ReviewItem();
        reviewItem.setTitle(reviewFormDto.getTitle());
        reviewItem.setContent(reviewFormDto.getContent());
        reviewItem.setRate(reviewFormDto.getRate());
        reviewItem.setMember(member);
        reviewItem.setOrderItemId(orderItemId);
        return reviewItem;
    }

}