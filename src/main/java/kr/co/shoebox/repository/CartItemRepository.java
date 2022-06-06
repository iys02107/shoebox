package kr.co.shoebox.repository;

import kr.co.shoebox.dto.CartDetailDto;
import kr.co.shoebox.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new kr.co.shoebox.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, ci.size220Count, ci.size225Count, ci.size230Count, ci.size235Count, ci.size240Count, ci.size245Count,  ci.size250Count,  ci.size255Count,  ci.size260Count,  ci.size265Count,  ci.size270Count,  ci.size275Count,  ci.size280Count,  ci.size285Count,  ci.size290Count, ci.size295Count, ci.size300Count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repImgYn = 'Y' " +
            "order by ci.regTime desc"
            )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);

}