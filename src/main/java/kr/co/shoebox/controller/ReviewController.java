package kr.co.shoebox.controller;



import kr.co.shoebox.dto.*;
import kr.co.shoebox.entity.*;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.OrderItemRepository;
import kr.co.shoebox.repository.OrderRepository;
//import kr.co.shoebox.repository.ReviewRepository;
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

    //    private final ReviewRepository reviewRepository;
    private final OrderItemRepository orderItemRepository;


    @GetMapping(value = "/new")
    public String reviewForm(Model model, @RequestParam("orderItemId") Long orderItemId){
        model.addAttribute("reviewFormDto", new ReviewFormDto());
        model.addAttribute("orderItemId", orderItemId);
        return "review/reviewForm";
    }

    @PostMapping(value = "/new")
    public String newReview(@Valid ReviewFormDto reviewFormDto, @RequestParam("orderItemId") Long orderItemId, Principal principal, Model model){

        ReviewItem reviewItem;

        try {
            reviewItem = reviewService.saveReview(reviewFormDto, orderItemId, principal.getName());
        } catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "review/reviewForm";
        }

        return "review/reviewMng";
    }

    @GetMapping(value = "/reviewMng")
    public String reviewMng(Principal principal, Model model){
        List<ReviewDetailDto> reviewDetailDtoList = reviewService.getReviewList(principal.getName());
        model.addAttribute("reviewItems", reviewDetailDtoList);
        return "review/reviewMng";
    }

    @GetMapping(value = "/update/{reviewItemId}")
    public String reviewUpdate(Model model, @RequestParam("reviewItemId") Long reviewItemId){
        model.addAttribute("reviewFormDto", new ReviewFormDto());
        model.addAttribute("reviewItemId", reviewItemId);
        return "review/reviewForm";
    }
    @PostMapping(value = "/update/{reviewItemId}")
    public String updateReview(@Valid ReviewFormDto reviewFormDto, @RequestParam("reviewItemId") Long reviewItemId, Principal principal, Model model){
        try {
            reviewService.updateReview(reviewFormDto, reviewItemId);
        } catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "review/reviewForm";
        }

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

//    @PostMapping(value = "/reviewForm/{orderItemId}")
//    public @ResponseBody ResponseEntity review(@RequestBody @Valid ReviewFormDto reviewFormDto, BindingResult bindingResult, @PathVariable("orderItemId") Long orderItemId){
//
//        if(bindingResult.hasErrors()){
//            StringBuilder sb = new StringBuilder();
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();3
//
//            for (FieldError fieldError : fieldErrors) {
//                sb.append(fieldError.getDefaultMessage());
//            }
//
//            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
//        }
//
//        Long reviewItemId;
//
//        try {
//            reviewItemId = reviewService.saveReview(reviewFormDto, orderItemId);
//
//        } catch(Exception e){
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<Long>(reviewItemId, HttpStatus.OK);
//    }


}