package kr.co.shoebox.service;

import kr.co.shoebox.constant.ItemSellStatus;
import kr.co.shoebox.dto.ItemFormDto;
import kr.co.shoebox.entity.Item;
import kr.co.shoebox.entity.ItemImg;
import kr.co.shoebox.repository.ItemImgRepository;
import kr.co.shoebox.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception{

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0;i<5;i++){
            String path = "C:/shoebox/item/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveItem() throws Exception {
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setItemNm("테스트상품");
        itemFormDto.setBrand("나이키");
        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
        itemFormDto.setItemDetail("테스트 상품 입니다.");
        itemFormDto.setPrice(1000);
        itemFormDto.setSize220(100);
        itemFormDto.setSize225(100);
        itemFormDto.setSize230(100);
        itemFormDto.setSize235(100);
        itemFormDto.setSize240(100);
        itemFormDto.setSize245(100);
        itemFormDto.setSize250(100);
        itemFormDto.setSize255(100);
        itemFormDto.setSize260(100);
        itemFormDto.setSize265(100);
        itemFormDto.setSize270(100);
        itemFormDto.setSize275(100);
        itemFormDto.setSize280(100);
        itemFormDto.setSize285(100);
        itemFormDto.setSize290(100);
        itemFormDto.setSize295(100);
        itemFormDto.setSize300(100);

        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);

        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(itemFormDto.getItemNm(), item.getItemNm());
        assertEquals(itemFormDto.getBrand(), item.getBrand());
        assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
        assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());
        assertEquals(itemFormDto.getPrice(), item.getPrice());
        assertEquals(itemFormDto.getSize220(), item.getSize220());
        assertEquals(itemFormDto.getSize225(), item.getSize225());
        assertEquals(itemFormDto.getSize230(), item.getSize230());
        assertEquals(itemFormDto.getSize235(), item.getSize235());
        assertEquals(itemFormDto.getSize240(), item.getSize240());
        assertEquals(itemFormDto.getSize245(), item.getSize245());
        assertEquals(itemFormDto.getSize250(), item.getSize250());
        assertEquals(itemFormDto.getSize255(), item.getSize255());
        assertEquals(itemFormDto.getSize260(), item.getSize260());
        assertEquals(itemFormDto.getSize265(), item.getSize265());
        assertEquals(itemFormDto.getSize270(), item.getSize270());
        assertEquals(itemFormDto.getSize275(), item.getSize275());
        assertEquals(itemFormDto.getSize280(), item.getSize280());
        assertEquals(itemFormDto.getSize285(), item.getSize285());
        assertEquals(itemFormDto.getSize290(), item.getSize290());
        assertEquals(itemFormDto.getSize295(), item.getSize295());
        assertEquals(itemFormDto.getSize300(), item.getSize300());
        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());
    }

}