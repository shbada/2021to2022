package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 자식명칭 지정 (부모테이블의 Dtype에 들어갈 명칭)
public class Album extends Item {
    private String artist;
}
