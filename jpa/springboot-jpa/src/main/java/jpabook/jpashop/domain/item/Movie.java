package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") /* dtype 에 들어갈 값 설정 */
@Getter @Setter
public class Movie extends Item {
    private String director;
    private String actor;
}
