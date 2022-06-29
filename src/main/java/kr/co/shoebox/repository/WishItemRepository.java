package kr.co.shoebox.repository;


import kr.co.shoebox.dto.WishItemDto;
import kr.co.shoebox.entity.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishItemRepository extends JpaRepository<WishItem, Long> {

   @Query("select wi from WishItem wi where wi.wish.id = :wishId and wi.item.id = :itemId")
   WishItem findWishItem(Long wishId, Long itemId);


    @Query("select new kr.co.shoebox.dto.WishItemDto(wi.id, i.itemNm, i.itemDetail, im.imgUrl, i.price, i.id) " +
            "from WishItem wi, ItemImg im " +
            "join wi.item i " +
            "where wi.wish.id = :wishId " +
            "and im.item.id = wi.item.id " +
            "and im.repImgYn = 'Y' " +
            "order by wi.regTime desc"
            )
    List<WishItemDto> findWishItemDtoList(Long wishId);



}