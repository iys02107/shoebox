package kr.co.shoebox.controller;

import kr.co.shoebox.constant.Role;
import kr.co.shoebox.dto.*;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.Member;
import kr.co.shoebox.repository.MemberRepository;
import kr.co.shoebox.repository.QnARepository;
import kr.co.shoebox.repository.ReviewItemRepository;
import kr.co.shoebox.repository.WishItemRepository;
import kr.co.shoebox.service.ItemService;
import kr.co.shoebox.service.QnAService;
import kr.co.shoebox.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final WishItemRepository wishItemRepository;
    private final MemberRepository memberRepository;

    private final ReviewService reviewService;

    private final ReviewItemRepository reviewItemRepository;

    private final QnARepository qnARepository;

    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){

        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/admin/item/{itemId}")
    public String itemForm(@PathVariable("itemId") Long itemId, Model model){

        try {
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
        } catch(EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "item/itemForm";
        }

        return "item/itemForm";
    }

    @PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model){
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
            itemService.updateItem(itemFormDto, itemImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "item/itemMng";
    }

    @GetMapping(value = "/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId, Principal principal){
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        ReviewCalcDto reviewCalcDto = reviewService.getReviewCalc(itemId);
        List<ReviewItemDto> reviewItemDtoList = reviewItemRepository.findReviewItemList(itemId);
        List<QnADto> qnaList = qnARepository.findQnAByItemId(itemId);
        String payId = itemService.getPayId();

        model.addAttribute("item", itemFormDto);
        model.addAttribute("review", reviewCalcDto);
        model.addAttribute("reviewItems", reviewItemDtoList);
        model.addAttribute("qnaCount", qnaList.size());
        model.addAttribute("qnaList", qnaList);
        model.addAttribute("payId", payId);


        if(principal!=null){
            Member member = memberRepository.findByEmail(principal.getName());
            try {
                model.addAttribute("member",member);
                Long wishId = member.getWish().getId();
                Long wishItemId = wishItemRepository.findWishItem(wishId,itemId).getItem().getId();
                if(itemId.equals(wishItemId)){
                    model.addAttribute("wishStatus",true);
                } else {
                    model.addAttribute("wishStatus", false);
                }
            } catch (NullPointerException e) {
                System.out.println("Null Pointer: "+e.getMessage());
            }
        }
        return "item/itemDtl";
    }



}