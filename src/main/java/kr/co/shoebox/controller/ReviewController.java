package kr.co.shoebox.controller;



import kr.co.shoebox.dto.*;
import kr.co.shoebox.entity.*;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.OrderItemRepository;
import kr.co.shoebox.repository.OrderRepository;
//import kr.co.shoebox.repository.ReviewRepository;
import kr.co.shoebox.service.OrderService;
import kr.co.shoebox.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")

public class ReviewController {

    private final ReviewService reviewService;

    private final MemberRepository memberRepository;


    @GetMapping(value = "/new/{orderItemId}")
    public String reviewForm(Model model, @PathVariable("orderItemId") Long orderItemId){
        model.addAttribute("reviewFormDto", new ReviewFormDto());
        model.addAttribute("orderItemId", orderItemId);
        return "review/reviewForm";
    }

    @PostMapping(value = "/new")
    public String newReview(@Valid ReviewFormDto reviewFormDto, @RequestParam("orderItemId") Long orderItemId, Principal principal, Model model){

        try {
            Member member = memberRepository.findByEmail(principal.getName());
            ReviewItem reviewItem = ReviewItem.createReviewItem(reviewFormDto,member,orderItemId);
            reviewService.saveReview(reviewItem, orderItemId);
        } catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "review/reviewForm";
        }

        return "review/reviewAlert";
    }

    @GetMapping(value = "/reviewMng")
    public String reviewMng(Principal principal, Model model){
        List<ReviewDetailDto> reviewDetailDtoList = reviewService.getReviewList(principal.getName());
        Member member = memberRepository.findByEmail(principal.getName());
        model.addAttribute("name", member.getName());
        model.addAttribute("reviewItems", reviewDetailDtoList);
        return "review/reviewMng";
    }

    @PostMapping(value = "/delete/{reviewItemId}")
    public @ResponseBody ResponseEntity reviewDelete(@PathVariable("reviewItemId") Long reviewItemId , Principal principal){

        if(!reviewService.validateReview(reviewItemId, principal.getName())){
            return new ResponseEntity<String>("리뷰 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        reviewService.deleteReview(reviewItemId);
        return new ResponseEntity<Long>(reviewItemId, HttpStatus.OK);
    }

}