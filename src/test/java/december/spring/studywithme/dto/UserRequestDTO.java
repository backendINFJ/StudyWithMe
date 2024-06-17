package december.spring.studywithme.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {


    private String email;
    private String introduce;
    private String name;
    private String password;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void createUserTest() {

        //given
        UserRequestDTO userRequestDTO = new UserRequestDTO();

        String userID = "yj123456789";
        String password = "Test1234!@";
        String name = "KimTest";
        String email = "test@gmail.com";
        String introduce = "테스트 입니다~";

        //when
        userRequestDTO.setUserId(userID);
        userRequestDTO.setPassword(password);
        userRequestDTO.setName(name);
        userRequestDTO.setEmail(email);
        userRequestDTO.setIntroduce(introduce);

        //then
        assertEquals(userID, userRequestDTO.getUserId());
        assertEquals(password, userRequestDTO.getPassword());
        assertEquals(name, userRequestDTO.getName());
        assertEquals(email, userRequestDTO.getEmail());
        assertEquals(introduce, userRequestDTO.getIntroduce());
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}