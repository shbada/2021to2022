package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.form.BookForm;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * 상품 등록 화면 이동
     * @param model
     * @return
     */
    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    /**
     * 상품 등록
     * @param bookForm
     * @return
     */
    @PostMapping("items/new")
    public String create(BookForm bookForm) {
        /* 원래는 setter 제거하고 createBook static 메소드를 entity 안에 넣어서 호출하는게 깔끔한 설계이다. */
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    /**
     * 상품 목록 조회
     * @param model
     * @return
     */
    @GetMapping("/items")
    public String list(Model model) {
        List<Item> itmes = itemService.findItems();
        model.addAttribute("items", itmes);
        return "items/itemList";
    }

    /**
     * 상품 수정 화면 이동
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book book = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setName(book.getName());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getStockQuantity());
        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    /**
     * 상품 수정
     * @param itemId
     * @param form
     * @return
     */
    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form) {
        // 화면에서 넘어온 데이터 form
        // Entity Book 에 데이터를 setting ((form) : JPA 를 거쳐서 온 객체 - 준영속 상태의 객체)
        // book 객체는 직접 new 를 통해 생성한 객체이므로 JPA 가 관리하지 못한다. (변경감지 불가능)

        // BookForm 을 통해서 데이터가 넘어오는데, Book 에 id 가 셋팅된다.
        // 뭔가 이건 JPA에서 한번 들어갔다가 나온 객체다. (id가 있으니까)
        // 식별자가 정확하게 있을때, 이를 준영속 상태의 객체라고 한다.
        // Book은 새로운 객체지만 JPA가 식별할 수 있는 객체를 가지고있다는 것
        // 근데 영속석 컨텍스트가 관리하지 않으니 준영속 상태라고 한다.
        // JPA가 관리를 안하기 때문에 여기서 set** 으로 값을 바꿔도 update 쿼리는 발생하지 않는다.
        /**
         * 준영속 엔티티란?
         * 영속성 컨텍스트가 더는 관리하지 않는 엔티티이다.
         *
         * em.find() 해서 얻어온 Book 객체가 있다고 보자.
         * Book.setXX 를 실행하면 JPA 가 변경 감지를 하여, 해당 데이터를 update 한다. (commit 시점에 update 날리고 commit 하는 것이다)
         *
         * 준영속 엔티티를 수정하는 2가지 방법
         * 1) 변경 감지 기능 사용
         * 2) 병합(merge) 사용
         */
        /* CASE1. merge
        Book book = new Book();
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        */

        /* CASE2. 변경감지 메서드 호출 */
        itemService.updateItem2(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items"; /* 리스트로 리다이렉트 */
    }
}
