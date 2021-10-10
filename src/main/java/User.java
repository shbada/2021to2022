import lombok.*;

import java.time.LocalDateTime;

/**
 * 파일이 컴파일되면 롬복은 delombok 되어 어노테이션에 맞게 변환된다.
 */
@Getter /* get , 각각의 필드에도 적용 가능 */
@Setter /* set , 각각의 필드에도 적용 가능 */
@ToString /* toString() 오버라이드 메서드 */
@NoArgsConstructor /* 기본 생성자 (인자없는 생성자) : JPA 에서는 반드시 필요하다. */
@AllArgsConstructor /* 모든 필드들을 받는 생성자 */
@RequiredArgsConstructor /* 꼭 필요한 인자만을 받는 생성자 (@NoArgsConstructor 와 동일하게 동작, @NonNull 된 필드가 없을경우) */
@EqualsAndHashCode /* 객체의 독립성 체크로, 해당 메서드 구현을 권장하고있다. */
public class User { // extends Object (자바의 모든 클래스는 Object 를 상속받은 클래스)
    @NonNull /* 필수값이 된다. @RequiredArgsConstructor 선언시 필수값인 필드만 받는 생성자를 생성한다. */
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /** @EqualsAndHashCode 사용하면 아래 코드가 실행됨 */
//    public boolean equals(final Object o) {
//        if (o == this) return true;
//        if (!(o instanceof User)) return false;
//        final User other = (User) o;
//        if (!other.canEqual((Object) this)) return false;
//        final Object this$name = this.getName();
//        final Object other$name = other.getName();
//        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
//        final Object this$email = this.getEmail();
//        final Object other$email = other.getEmail();
//        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
//        final Object this$createdAt = this.getCreatedAt();
//        final Object other$createdAt = other.getCreatedAt();
//        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
//        final Object this$updatedAt = this.getUpdatedAt();
//        final Object other$updatedAt = other.getUpdatedAt();
//        if (this$updatedAt == null ? other$updatedAt != null : !this$updatedAt.equals(other$updatedAt)) return false;
//        return true;
//    }
//
//    protected boolean canEqual(final Object other) {
//        return other instanceof User;
//    }
//
//    public int hashCode() {
//        final int PRIME = 59;
//        int result = 1;
//        final Object $name = this.getName();
//        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
//        final Object $email = this.getEmail();
//        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
//        final Object $createdAt = this.getCreatedAt();
//        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
//        final Object $updatedAt = this.getUpdatedAt();
//        result = result * PRIME + ($updatedAt == null ? 43 : $updatedAt.hashCode());
//        return result;
//    }

    /** Constructor 총 3가지 */
    // @AllArgsConstructor
//    public User(@NonNull String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.name = name;
//        this.email = email;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//
//    // @NoArgsConstructor
//    public User() {
//    }
//
//    // @RequiredArgsConstructor
//    public User(@NonNull String name) {
//        this.name = name;
//    }

    /** toString */
    /* 이렇게 되면 필드를 추가할때마다 아래 메서드를 관리해줘야한다. 누락할수도 있고, 코드가 지저분해진다.*/
//    @Override // Object 클래스가 상속되어 있으므로, toString 오버라이드
//    public String toString() {
//        return getClass().getName() + " : " + "name: " + name + ", email: " + email
//                + ", createdAt : " + createdAt + ", updatedAt: " + updatedAt;
//    }

    /** getter/setter */
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
}
