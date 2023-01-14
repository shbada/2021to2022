package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
@Table(name="MEMBER")
public class Member2 {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    // Period
    //private LocalDateTime startDate;
    //private LocalDateTime endDate;
    @Embedded
    private Period period;

    // 주소
    //private String city;
    //private String street;
    //private String zipcode;
    @Embedded // 여기랑 Address 클래스 둘중에 1개만 어노테이션 있으면 되는데 둘자 쓰는거 권장
    private Address address;

    @Embedded // 같은 클래스 중복 사용 에러 발생 -> @AttributeOverride 사용
    private Address workAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOODS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();
}
