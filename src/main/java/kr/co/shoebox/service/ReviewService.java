package kr.co.shoebox.service;

import kr.co.shoebox.dto.ReviewDetailDto;
import kr.co.shoebox.dto.ReviewFormDto;
import kr.co.shoebox.entity.*;
import kr.co.shoebox.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional(readOnly = true)
    public List<ReviewDetailDto> getReviewList(String email){

        List<ReviewDetailDto> reviewDetailDtoList;

        Member member = memberRepository.findByEmail(email);

        reviewDetailDtoList = reviewItemRepository.findReviewMngList(member.getId());
        return reviewDetailDtoList;
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