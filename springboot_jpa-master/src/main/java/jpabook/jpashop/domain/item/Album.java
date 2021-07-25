package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") /* dtype 에 들어갈 값 설정 */
@Getter @Setter
public class Album extends Item {
    private String artist;
    private String etc;
}
