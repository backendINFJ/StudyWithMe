package december.spring.studywithme.entity;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jackson.generator.JacksonArbitraryGenerator;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.navercorp.fixturemonkey.api.experimental.JavaGetterMethodPropertySelector.javaGetter;
import static org.assertj.core.api.BDDAssertions.then;


public class FixtureMonkeyUserTest {


    @Test
    @DisplayName("회원가입 테스트")
    void createUser() {

        // given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE) // 객체 생성전략
                .build();

        // when
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("userId", "MonkeyTest")
                .set("password", "Test123456!@")
                .set("name", "픽쳐몽키")
                .set("email", "yj@gmail.com")
                .set("introduce", "제발 좀 되라!!")
                .sample();
        // then
        then(user.getUserId()).isEqualTo("MonkeyTest");
        then(user.getPassword()).isEqualTo("Test123456!@");
        then(user.getName()).isEqualTo("픽쳐몽키");
        then(user.getEmail()).isEqualTo("yj@gmail.com");
        then(user.getIntroduce()).isEqualTo("제발 좀 되라!!");
    }

    @Test
    @DisplayName("회원 상태 변경 - 탈퇴 회원")
    void whithdrawUser() {

        // given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE) // 객체 생성전략
                .build();

        // when
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("userType", UserType.UNVERIFIED)
                .sample();
        user.withdrawUser();

        // then
        then(user.getUserType()).isEqualTo(UserType.DEACTIVATED);
    }

    @Test
    @DisplayName("회원 상태 변경 - 인증 회원")
    void activeUser() {
        // given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE) // 객체 생성전략
                .build();

        // when
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("userType", UserType.UNVERIFIED)
                .sample();
        user.ActiveUser();

        // then

        then(user.getUserType()).isEqualTo(UserType.ACTIVE);
    }

    @Test
    @DisplayName("로그인시 리프레시 토큰 초기화")
    void refreshTokenReset() {

        // given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE) // 객체 생성전략
                .build();

        // when
        User user = fixtureMonkey.giveMeBuilder((User.class))
                .set("refreshToken", "refreshToken")
                .sample();

        // then
        then(user.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("프로필 수정")
    void editProfile() {

        // given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE) // 객체 생성전략
                .build();

        // when
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("name", "픽쳐몽키")
                .set("introduce", "제발 되었으면 좋겠다.")
                .sample();

        user.editProfile("픽쳐몽키수정", "제발 되라~!!~");
        // then
        then(user.getName()).isEqualTo("픽쳐몽키수정");
        then(user.getIntroduce()).isEqualTo("제발 되라~!!~");
    }

    @Test
    @DisplayName("비밀번호 변경")
    void changePassword() {

        // given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE) // 객체 생성전략
                .build();

        // when
        User user = fixtureMonkey.giveMeBuilder(User.class)
                .set("password", "1234")
                .sample();

        user.changePassword("5678");

        // then
        then(user.getPassword()).isEqualTo("5678");
    }
}