package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import static org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubReleasesTest {

    @Autowired
    TestRestTemplate restTemplate

    @Test
    void shouldReturnASaasSubscriptionWhenDataIsSaved() {
        ResponseEntity<List> response = restTemplate.getForEntity("/github/releases", List.class)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK)
        List l = response.getBody()
        assertThat(l).isNotEmpty()
        def release = l.get(0)
        assertThat(release).isNotNull()
        assertThat((Map) release).containsKey("url")
        assertThat((Map) release).containsKey("name")
	}

}
