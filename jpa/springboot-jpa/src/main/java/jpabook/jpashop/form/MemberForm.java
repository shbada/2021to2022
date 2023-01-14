package jpabook.jpashop.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 입니다") /* 컨트롤러 @Valid 어노테이션으로 체크를 해준다 */
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
