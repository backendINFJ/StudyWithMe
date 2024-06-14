package december.spring.studywithme.entity;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import december.spring.studywithme.dto.CommentRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class FixtureMonkeyCommentTest {


    private final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build(); // 공통 given

    private User user;

    private Post post;


    @Test
    @DisplayName("댓글 작성 테스트")
    void createComment() {



        // given
        Comment comment = fixtureMonkey.giveMeOne(Comment.class);



        // when
        comment = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("post", post)
                .set("user", user)
                .set("contents", "픽쳐몽키댓글")
                .sample();

        // then
        then(comment.getUser()).isEqualTo(user);
        then(comment.getPost()).isEqualTo(post);
        then(comment.getContents()).isEqualTo("픽쳐몽키댓글");

     }

     @Test
     @DisplayName("댓글 수정 테스트")
     void editComment() {

         // given
         Comment comment = fixtureMonkey.giveMeOne(Comment.class);
         CommentRequestDTO commentRequestDTO = new CommentRequestDTO();

         // when
         comment.update(commentRequestDTO);

         // then
         then(comment.getContents()).isEqualTo(commentRequestDTO.getContents());
      }

      @Test
      @DisplayName("댓글 좋아요/좋아요 취소")
       void editCommentLikez() {

          // given
          Comment comment = fixtureMonkey.giveMeOne(Comment.class);
          CommentLike commentLike = fixtureMonkey.giveMeOne(CommentLike.class);

          // when
          comment.updateCommentLikes(0L);

          // then
         then(comment.getLikes()).isEqualTo(0L);
       }
}
