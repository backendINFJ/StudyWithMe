package december.spring.studywithme.entity;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;


public class FixtureMonkeyUserTest {

    @Test
    public void testUser() {
        // Given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .build();

        // When
        User user = fixtureMonkey.giveMeOne(User.class);
        // Then
        then(user).isNotNull();
    }
}