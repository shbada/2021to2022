package com.api.shop.modules.service;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.entity.Order;
import com.api.shop.modules.form.OrderAddForm;
import com.api.shop.modules.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final MemberService memberService;

    /**
     * 회원의 주문 리스트 조회
     * @param memberIdx
     * @return
     */
    @Transactional
    public List<Order> getOrderListOfMember(long memberIdx) {
        Member member = memberService.getMember(memberIdx);

        if (member == null) {
            throw new BadRequestException("존재하지 않는 회원입니다.");
        }

        return member.getOrderList();
    }

    /**
     * 주문 등록
     * @param orderAddForm
     * @return
     */
    public Long addOrder(OrderAddForm orderAddForm) {
        Long orderIdx = 0L;

        return orderIdx;
    }
}
