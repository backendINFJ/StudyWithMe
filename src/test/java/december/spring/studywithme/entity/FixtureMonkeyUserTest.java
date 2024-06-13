package december.spring.studywithme.entity;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jackson.generator.JacksonArbitraryGenerator;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.Test;

import static com.navercorp.fixturemonkey.api.experimental.JavaGetterMethodPropertySelector.javaGetter;
import static org.assertj.core.api.BDDAssertions.then;


public class FixtureMonkeyUserTest {


    @Test
    void createUser() {

        // given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
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
}