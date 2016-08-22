package com.seb.tool.app.boot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.seb.tool.domain.customer.Customer;
import com.seb.tool.services.bundle.BundleService;
import com.seb.tool.services.bundle.BundleServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SebToolBootApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class BundleRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private BundleService bundleService;
	
	@Test
	public void testSuggestedBundle() {
		Customer customer = new Customer();
		customer.setAge(16);
		customer.setStudent(false);
		((BundleServiceImpl) bundleService).init();
		ResponseEntity<List> suggestedBundles = restTemplate.postForEntity("/bundle/", customer, List.class);
		assertThat(suggestedBundles.getStatusCode(), is(HttpStatus.CREATED));
	}
	
}
