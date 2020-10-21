package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.exception.UserRegistrationException;
import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.QueryExecutorResponse;
import com.intuit.practice.courtbookingbackend.model.User;
import com.intuit.practice.courtbookingbackend.model.UserResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserApiTest {
   @InjectMocks
   private UserApi userApi = spy(new UserApi());

   @Mock
   private User user;

   @Mock
   private QueryExecutor queryExecutor;

   @Mock
   private QueryExecutorResponse queryExecutorResponse;

   @Test
   public void validateRegisterUser() throws SQLException {
      doReturn(queryExecutorResponse).when(queryExecutor).executeQuery(anyString(), anyString(), anyString(), anyString(), anyString());
      doReturn(1).when(userApi).fetchUserId(anyString());
      doReturn(1).when(user).getUserId();
      doReturn("987654678").when(user).getPhoneNumber();
      doReturn("abc@gmail.com").when(user).getEmailId();
      doReturn("abcd").when(user).getFullName();

      UserResponse userResponse = userApi.registerUser(user);

      assertThat(userResponse.getUser().getUserId()).isEqualTo(user.getUserId());
      assertThat(userResponse.getUser().getPhoneNumber()).isEqualTo(user.getPhoneNumber());
      assertThat(userResponse.getUser().getUserId()).isEqualTo(user.getUserId());
      assertThat(userResponse.getUser().getEmailId()).isEqualTo(user.getEmailId());
   }

   /*@Test(expected = UserRegistrationException.class)
   public void validateFailureRegisterUser() throws SQLException {
      doThrow(new SQLException("Error while executing query")).when(queryExecutor).executeQuery(anyString(), anyString(), anyString(), anyString(), anyString());

      userApi.registerUser(user);
   }**/
}
