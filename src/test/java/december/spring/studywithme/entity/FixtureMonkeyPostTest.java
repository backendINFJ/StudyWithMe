package december.spring.studywithme.entity;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import december.spring.studywithme.dto.PostRequestDTO;
import december.spring.studywithme.utils.MonkeyUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import static org.assertj.core.api.BDDAssertions.then;

public class FixtureMonkeyPostTest extends MonkeyUtils {

    private final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build(); // 공통 given

    private User user;

    @Test
    @DisplayName("게시글 작성 테스트")
    void createPost() {

        //given


        // when


        Post post = fixtureMonkey.giveMeBuilder(Post.class)
                .set("user", user)
                .set("title", "픽쳐몽키제목")
                .set("contents", "픽쳐몽키내용")
                .sample();


        // then
        then(post.getUser()).isEqualTo(user);
        then(post.getTitle()).isEqualTo("픽쳐몽키제목");
        then(post.getContents()).isEqualTo("픽쳐몽키내용");

    }

    @Test
    @DisplayName("게시물 수정 테스트")
    void editPost() {

        // given

        Post post = fixtureMonkey.giveMeOne(Post.class);
        PostRequestDTO postRequestDTO = new PostRequestDTO();


        // when
        post.update(postRequestDTO);
        // then

        then(post.getTitle()).isEqualTo(postRequestDTO.getTitle());
        then(post.getContents()).isEqualTo(postRequestDTO.getContents());
    }

    @Test
    @DisplayName("게시글 좋아요/좋아요취소 테스트")
    void editPostLikes() {

            // given
        Post post = fixtureMonkey.giveMeOne(Post.class);
        PostLike postLike = fixtureMonkey.giveMeOne(PostLike.class);
            // when
            post.updatePostLikes(0L);
            // then

        then(post.getLikes()).isEqualTo(0L);
         }
    }
