package com.api.shop.modules.service;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.modules.entity.Cart;
import com.api.shop.modules.entity.Item;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.CartAddForm;
import com.api.shop.modules.repository.CartRepository;
import com.api.shop.modules.repository.ItemRepository;
import com.api.shop.modules.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    /**
     * 장바구니 등록
     * @param cartAddForm
     * @return
     */
    @Transactional
    public Long addCart(CartAddForm cartAddForm) {
        Optional<Member> member = memberRepository.findById(cartAddForm.getMemberIdx());
        if (member.isEmpty()) {
            throw new BadRequestException("존재하지 않는 회원입니다.");
        }

        Optional<Item> item = itemRepository.findById(cartAddForm.getItemIdx());
        if (item.isEmpty()) {
            throw new BadRequestException("존재하지 않는 회원입니다.");
        }

        Cart cart = Cart.addCart(member.get(), item.get(), cartAddForm.getCount());

        cartRepository.save(cart);

        return cart.getIdx();
    }
}
