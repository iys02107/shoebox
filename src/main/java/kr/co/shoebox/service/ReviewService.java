package kr.co.shoebox.service;

import kr.co.shoebox.dto.ReviewCalcDto;
import kr.co.shoebox.dto.ReviewDetailDto;
import kr.co.shoebox.dto.ReviewFormDto;
import kr.co.shoebox.dto.ReviewItemDto;
import kr.co.shoebox.entity.*;
import kr.co.shoebox.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
//    private final ReviewRepository reviewRepository;

    private final OrderItemRepository orderItemRepository;

    private final OrderRepository orderRepository;

    private final ReviewItemRepository reviewItemRepository;

    public ReviewItem saveReview(ReviewFormDto reviewFormDto, Long orderItemId, String email){

        ReviewItem reviewItem = reviewItemRepository.findReviewItem(orderItemId);
        Member member = memberRepository.findByEmail(email);

        if(reviewItem == null){
            reviewItem = ReviewItem.createReviewItem(reviewFormDto, member, orderItemId);
            reviewItemRepository.save(reviewItem);
            return reviewItem;
        }else {
            throw new IllegalStateException("이미 리뷰 등록하였습니다.");
        }

    }

    public void updateReview(ReviewFormDto reviewFormDto, Long reviewItemId){
       reviewItemRepository.updateReviewItem(reviewFormDto.getRate(), reviewFormDto.getTitle(), reviewFormDto.getContent(), reviewItemId);
    }

    public void deleteReview(Long reviewItemId){
        reviewItemRepository.deleteById(reviewItemId);
    }


    @Transactional(readOnly = true)
    public List<ReviewDetailDto> getReviewList(String email){

        List<ReviewDetailDto> reviewDetailDtoList;

        Member member = memberRepository.findByEmail(email);

        reviewDetailDtoList = reviewItemRepository.findReviewMngList(member.getId());
        return reviewDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateReview(Long reviewId, String email){
        Member curMember = memberRepository.findByEmail(email);
        ReviewItem reviewItem = reviewItemRepository.findById(reviewId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = reviewItem.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }

    @Transactional(readOnly = true)
    public ReviewCalcDto getReviewCalc(Long itemId){

        int count = reviewItemRepository.findReviewNull(itemId);

        if(count == 0){
            ReviewCalcDto reviewCalcDto = new ReviewCalcDto();
            reviewCalcDto.setRate(0);
            reviewCalcDto.setCount(Long.valueOf("0"));
            return reviewCalcDto;
        }else {
            ReviewCalcDto reviewCalcDto2 = reviewItemRepository.findReviewCalc(itemId);
            return reviewCalcDto2;
        }

    }

    @Transactional(readOnly = true)
    public List<ReviewItemDto> getReviewItemList(Long itemId){

        List<ReviewItemDto> reviewItemDtoList;

        reviewItemDtoList = reviewItemRepository.findReviewItemList(itemId);
        return reviewItemDtoList;
    }



//
//    private void validateDuplicateReview(Review review){
//        Review findReview = reviewRepository.findReview(review.getOrder().getId());
//        if(findReview != null){
//            throw new IllegalStateException("이미 리뷰 작성이 완료되었습니다.");
//        }
//    }

//    @Transactional(readOnly = true)
//    public int getReviewTotal(Long itemId){
//        Item item = itemRepository.getById(itemId);
//        List<ReviewDetailDto> reviewListForCount = reviewRepository.findReviewList(item.getId());
//
//        int reviewTotalCount = 0;
//
//        if(reviewListForCount == null){
//            return reviewTotalCount;
//        }else{
//           reviewTotalCount = reviewListForCount.toArray().length;
//        };
//        return reviewTotalCount;
//    }


}