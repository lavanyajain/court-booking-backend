package com.intuit.practice.courtbookingbackend;

import com.intuit.practice.courtbookingbackend.api.UserApi;
import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.User;
import com.intuit.practice.courtbookingbackend.model.UserResponse;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class CourtBookingBackendApplicationTests {

	@InjectMocks
	@Spy
	private UserApi userApi;

	@Mock
	private User user;

	@Mock
	private QueryExecutor queryExecutor;

	@Test
	void validateRegisterUser() throws SQLException {
		doNothing().when(queryExecutor).executeUpdate(anyString(), anyString(), anyString(), anyString(), anyString());
		doReturn(1).when(userApi).fetchUserId(anyString());
		doReturn("Lavanya").when(user).getFullName();
		doReturn("Lavanya@gmail.com").when(user).getEmailId();
		doReturn("56887654").when(user).getPhoneNumber();
		doReturn(1).when(user).getUserId();

		UserResponse response = userApi.registerUser(user);

		assertThat(response.getStatus()).isEqualTo("success");
		assertThat(response.getUser().getEmailId()).isEqualTo(user.getEmailId());
		assertThat(response.getUser().getFullName()).isEqualTo(user.getFullName());
		assertThat(response.getUser().getPhoneNumber()).isEqualTo(user.getPhoneNumber());
		assertThat(response.getUser().getUserId()).isEqualTo(user.getUserId());
	}
}
