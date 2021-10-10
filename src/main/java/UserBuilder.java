import lombok.*;

import java.time.LocalDateTime;

/**
 * 파일이 컴파일되면 롬복은 delombok 되어 어노테이션에 맞게 변환된다.
 */
/* Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode. */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class UserBuilder { // extends Object (자바의 모든 클래스는 Object 를 상속받은 클래스)
    @NonNull
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /** @Builder 어노테이션 선언시 */
//    public static UserBuilderBuilder builder() {
//        return new UserBuilderBuilder();
//    }
//
//
//    public static class UserBuilderBuilder {
//        private @NonNull String name;
//        private String email;
//        private LocalDateTime createdAt;
//        private LocalDateTime updatedAt;
//
//        UserBuilderBuilder() {
//        }
//
//        public UserBuilderBuilder name(@NonNull String name) {
//            this.name = name;
//            return this;
//        }
//
//        public UserBuilderBuilder email(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public UserBuilderBuilder createdAt(LocalDateTime createdAt) {
//            this.createdAt = createdAt;
//            return this;
//        }
//
//        public UserBuilderBuilder updatedAt(LocalDateTime updatedAt) {
//            this.updatedAt = updatedAt;
//            return this;
//        }
//
//        public UserBuilder build() {
//            return new UserBuilder(name, email, createdAt, updatedAt);
//        }
//
//        public String toString() {
//            return "UserBuilder.UserBuilderBuilder(name=" + this.name + ", email=" + this.email + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
//        }
//    }
}
