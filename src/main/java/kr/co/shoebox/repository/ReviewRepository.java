//package kr.co.shoebox.repository;
//
//import kr.co.shoebox.dto.ReviewDetailDto;
//import kr.co.shoebox.dto.ReviewFormDto;
//import kr.co.shoebox.entity.Review;
//import kr.co.shoebox.entity.ReviewItem;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
//
//import java.util.List;
//
//public interface ReviewRepository extends JpaRepository<Review, Long>,
//        QuerydslPredicateExecutor<Review>, ItemRepositoryCustom {
//
////
////    @Query("select new kr.co.shoebox.dto.ReviewDetailDto(ri.id, ri.title, ri.content, ri.reviewImgUrl, ri.rate, o.id) " +
////            "from ReviewItem ri, Review r " +
////            "join r.order o " +
////            "where ri.item.id = :itemId")
////    List<ReviewDetailDto> findReviewList(Long itemId);
//
//    @Query("select r from Review r where r.orderItem.id = :orderItemId")
//    Review findReview(Long orderItemId);
//
//}