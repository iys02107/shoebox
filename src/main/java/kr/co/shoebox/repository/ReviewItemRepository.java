package kr.co.shoebox.repository;


import kr.co.shoebox.dto.ReviewCalcDto;
import kr.co.shoebox.dto.ReviewDetailDto;
import kr.co.shoebox.dto.ReviewItemDto;
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

    @Query("select new kr.co.shoebox.dto.ReviewDetailDto(ri.id, ri.content, ri.rate, i.id, im.imgUrl, i.itemNm, ri.regTime) " +
            "from ReviewItem ri, ItemImg im, OrderItem oi " +
            "join oi.item i " +
            "where ri.member.id = :memberId " +
            "and ri.orderItemId = oi.id " +
            "and oi.item.id = im.item.id " +
            "and im.repImgYn = 'Y' " +
            "order by ri.regTime desc")
    List<ReviewDetailDto> findReviewMngList(Long memberId);

    @Transactional
    @Query("select new kr.co.shoebox.dto.ReviewCalcDto(avg(ri.rate) as rate, count(*) as count) " +
            "from ReviewItem ri, OrderItem oi " +
            "join oi.item i " +
            "where ri.orderItemId = oi.id " +
            "and oi.item.id = :itemId " +
            "order by ri.regTime desc")
    ReviewCalcDto findReviewCalc(Long itemId);

    @Query("select count(*) " +
            "from ReviewItem ri, OrderItem oi " +
            "join oi.item i " +
            "where ri.orderItemId = oi.id " +
            "and oi.item.id = :itemId")
    int findReviewNull(Long itemId);

    @Query("select new kr.co.shoebox.dto.ReviewItemDto(ri.id, ri.content, ri.rate, ri.member.id, ri.regTime) " +
            "from ReviewItem ri, OrderItem oi " +
            "join oi.item i " +
            "where ri.orderItemId = oi.id " +
            "and oi.item.id = :itemId " +
            "order by ri.regTime desc")
    List<ReviewItemDto> findReviewItemList(Long itemId);

}