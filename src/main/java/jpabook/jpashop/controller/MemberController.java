package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.form.MemberForm;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 등록 화면 이동
     * @param model
     * @return
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    /**
     * 회원 저장
     * @param memberForm
     * @return
     */
    @PostMapping("/members/new") /* Member Entity 를 쓰는것 보다는 form 으로 하는게 좋다. 서로 데이터가 맞지않을 수 있다. */
    public String create(@Valid MemberForm memberForm, BindingResult result) { /* @Valid 다음에 BindingResult가 있으면 정보가 담겨진다 */
        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; /* 홈 화면으로 리다이렉트 */
    }

    /**
     * 회원 리스트 조회
     * @param model
     * @return
     */
    @GetMapping("/members")
    public String list(Model model) {
        /*
            API 를 만들때는 엔티티를 외부로 반환하면 안된다.
            Entity 가 변경되면 API 스펙이 변경된다.
            하지만 템플릿 엔진에서는 필요한 데이터를 꺼내 쓰기때문에 괜찮다.
         */
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
