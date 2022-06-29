package kr.co.shoebox.controller;


import kr.co.shoebox.dto.WishItemDto;
import kr.co.shoebox.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @GetMapping(value = "/wish")
    public String wishList(Principal principal, Model model){
        List<WishItemDto> wishItemDtoList = wishService.getWishList(principal.getName());
        model.addAttribute("wishItems", wishItemDtoList);
        return "wish/wishList";
    }

    @PostMapping(value = "/wish")
    public @ResponseBody ResponseEntity wish(@RequestBody @Valid WishItemDto wishItemDto, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long wishItemId;

        try {
            wishItemId = wishService.addWish(wishItemDto, email);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(wishItemId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/unWish")
    public @ResponseBody ResponseEntity deleteWishItem(@RequestBody WishItemDto wishItemDto, Principal principal){

        List<WishItemDto> wishItemDtoList = wishItemDto.getWishItemDtoList();

        for(WishItemDto wishItem : wishItemDtoList) {
            wishService.unWishItem(wishItemDtoList);
            Long wishItemId = wishItem.getWishItemId();
            return new ResponseEntity<Long>(wishItemId, HttpStatus.OK);
        }
        return new ResponseEntity<List>(wishItemDtoList, HttpStatus.OK);
    }


}