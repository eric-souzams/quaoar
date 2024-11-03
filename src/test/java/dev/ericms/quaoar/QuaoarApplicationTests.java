package dev.ericms.quaoar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class QuaoarApplicationTests {

	@Test
	void contextLoads() {
		QuaoarApplication.main(new String[] {});
	}
}
