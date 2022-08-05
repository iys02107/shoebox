package kr.co.shoebox.repository;


import kr.co.shoebox.dto.ReviewDetailDto;
import kr.co.shoebox.entity.ReviewItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface ReviewItemRepository extends JpaRepository<ReviewItem, Long> {

    @Query("select ri " +
            "from ReviewItem ri, OrderItem oi " +
            "where ri.orderItemId = oi.id " +
            "and ri.orderItemId = :orderItemId")
    ReviewItem findReviewItem(Long orderItemId);

    @Query("select ri " +
            "from ReviewItem ri, OrderItem oi " +
            "where ri.orderItemId = oi.id " +
            "and ri.member.id = :memberId")
    ReviewItem findReviewItemByMemberId(Long memberId);

    @Query("select new kr.co.shoebox.dto.ReviewDetailDto(ri.id, ri.title, ri.content, ri.rate, i.id, im.imgUrl, i.itemNm) " +
            "from ReviewItem ri, ItemImg im, OrderItem oi " +
            "join oi.item i " +
            "where ri.member.id = :memberId " +
            "and ri.orderItemId = oi.id " +
            "and oi.item.id = im.item.id " +
            "and im.repImgYn = 'Y' " +
            "order by ri.regTime desc")
    List<ReviewDetailDto> findReviewMngList(Long memberId);

    @Modifying
    @Transactional
    @Query("update ReviewItem ri " +
            "set ri.rate = :rate, ri.title = :title, ri.content = :content " +
            "where ri.id = :id")
    void updateReviewItem(String rate, String title, String content, Long id);

//    @Query("select new kr.co.shoebox.dto.ReviewDetailDto(ri.reviewId, ri.title, ri.content, ri.rate, ii.id, ii.imgUrl, ii.itemNm) " +
//            "from ReviewItem ri, (select i.id id, im.imgUrl imgUrl, i.itemNm itemNm " +
//                                    "from ItemImg im, OrderItem oi " +
//                                    "join oi.item i " +
//                                    "where oi.id = :orderItemId " +
//                                    "and oi.item.id = im.item.id " +
//                                    "and im.repImgYn = 'Y') ii")
//    List<ReviewDetailDto> findReviewList(Long orderItemId);
}