package example.clinicappt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.net.URI;

import example.clinicappt.models.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // start app to make it available for test to perform request to it
class ClinicApptApplicationTests {
	@Autowired // instructs Spring to inject an HTTP request test help
	TestRestTemplate restTemplate;

	ResponseEntity<Void> createUser(String firstName, String lastName, int age, String email) {
		User newUser = new User(firstName, lastName, age, email);
		return restTemplate.postForEntity("/user/auth/signup", newUser, Void.class);
	}

	@DisplayName("Create User")
	@Test
	@DirtiesContext
	void shouldCreateANewUser() {
		ResponseEntity<Void> createResponse = createUser("Kharka", "Kay", 26, "kkay@mail.com");
		URI locationOfNewUser = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUser, String.class);

		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@DisplayName("Return User Info")
	@Test
	void shouldReturnUserInfo() {
		ResponseEntity<Void> createResponse = createUser("Kharka", "Kay", 26, "kkay@mail.com");
		URI locationOfNewUser = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUser, String.class);
		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Number id = documentContext.read("$.id");
		String userFirstName = documentContext.read("$.firstName");

		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(id).isNotNull();
		assertThat(userFirstName).isEqualTo("Kharka");
	}

	@DisplayName("No User Info for invalid Id")
	@Test
	void shouldNotReturnUserWhenIdIsInvalid() {
		ResponseEntity<String> response = restTemplate.getForEntity("/user/10", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}
}








