//package kr.co.shoebox.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "review")
//@Getter @Setter
//@ToString
//public class Review extends BaseEntity {
//
//    @Id
//    @Column(name = "review_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//
////    @OneToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name="member_id")
////    private Member member;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_item_id")
//    private OrderItem orderItem;
//
//
//    public static Review createReview(OrderItem orderItem){
//        Review review = new Review();
//        review.setOrderItem(orderItem);
//        return review;
//    }
//
//}