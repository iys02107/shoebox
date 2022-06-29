package kr.co.shoebox.service;

import kr.co.shoebox.dto.*;
import kr.co.shoebox.entity.*;
import kr.co.shoebox.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    private final WishRepository wishRepository;

    private final WishItemRepository wishItemRepository;

    public Long addWish(WishItemDto wishItemDto, String email) {

        Item item = itemRepository.findById(wishItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);


        Wish wish = wishRepository.findByMemberId(member.getId());
        if (wish == null) {
            wish = Wish.createWish(member);
            wishRepository.save(wish);
        }

        WishItem savedWishItem = wishItemRepository.findWishItem(wish.getId(), item.getId());

        if(savedWishItem != null && savedWishItem.getItem().getId().equals(wishItemDto.getItemId())){
            return savedWishItem.getId();
        } else {
            WishItem wishItem = WishItem.createWishItem(wish, item);
            wishItemRepository.save(wishItem);
            return wishItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<WishItemDto> getWishList(String email){

        List<WishItemDto> wishItemDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        Wish wish = wishRepository.findByMemberId(member.getId());
        if(wish == null){
            return wishItemDtoList;
        }

        wishItemDtoList = wishItemRepository.findWishItemDtoList(wish.getId());

        return wishItemDtoList;
    }


    public void unWishItem(List<WishItemDto> wishItemDtoList) {
        for (WishItemDto wishItemDto : wishItemDtoList) {
            WishItem wishItem = wishItemRepository
                    .findById(wishItemDto.getWishItemId())
                    .orElseThrow(EntityNotFoundException::new);
            wishItemRepository.delete(wishItem);
        }
    }

}