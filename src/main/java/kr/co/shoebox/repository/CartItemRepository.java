package kr.co.shoebox.repository;

import kr.co.shoebox.dto.CartDetailDto;
import kr.co.shoebox.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

   @Query("select ci from CartItem ci where ci.cart.id = :cartId and ci.item.id = :itemId and ci.size = :size")
   CartItem findCartItem(Long cartId, Long itemId, String size);


    @Query("select new kr.co.shoebox.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, ci.size, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repImgYn = 'Y' " +
            "order by ci.regTime desc"
            )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);



}