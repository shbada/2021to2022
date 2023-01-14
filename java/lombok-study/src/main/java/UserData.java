import lombok.*;

import java.time.LocalDateTime;

/**
 * 파일이 컴파일되면 롬복은 delombok 되어 어노테이션에 맞게 변환된다.
 */
/* Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode. */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData { // extends Object (자바의 모든 클래스는 Object 를 상속받은 클래스)
    @NonNull
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /** @Data 선언시*/
//    public UserData() {
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return this.createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return this.updatedAt;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public boolean equals(final Object o) {
//        if (o == this) return true;
//        if (!(o instanceof UserData)) return false;
//        final UserData other = (UserData) o;
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
//        return other instanceof UserData;
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
//
//    public String toString() {
//        return "UserData(name=" + this.getName() + ", email=" + this.getEmail() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
//    }
}
