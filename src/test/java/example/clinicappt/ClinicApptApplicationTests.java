package example.clinicappt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // start app to make it available for test to perform request to it
class ClinicApptApplicationTests {
	@Autowired // instructs Spring to inject an HTTP request test help
	TestRestTemplate restTemplate;

	@DisplayName("Create User - Returns User Info")
	@Test
	void shouldReturnUserInfoWhenUserIsCreated() {
		ResponseEntity<String> response = restTemplate.getForEntity("/user/auth/get-profile/12345", String.class);
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		String id = documentContext.read("$.id");

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(id).isNotNull();
	}
}
