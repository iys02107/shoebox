package kr.co.shoebox.dto;

import kr.co.shoebox.constant.ItemSellStatus;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.ReviewItem;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "브랜드는 필수 입력 값입니다.")
    private String brand;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size220;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size225;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size230;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size235;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size240;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size245;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size250;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size255;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size260;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size265;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size270;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size275;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size280;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size285;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size290;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size295;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer size300;

    private List<ReviewDetailDto> reviewDetailDtoList = new ArrayList<>();

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item,ItemFormDto.class);
    }

}