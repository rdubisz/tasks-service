package net.rd.tasks.service.rest;

import net.rd.tasks.service.jpa.DatabaseConfiguration;
import net.rd.tasks.service.model.TaskDefinitionModel;
import net.rd.tasks.service.model.TaskOperationModel;
import net.rd.tasks.service.model.TaskOperationQueryParamModel;
import net.rd.tasks.service.model.TaskOperationQueryResultModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

import static net.rd.tasks.service.rest.RestSecurityConfig.WEB_SECRET;
import static net.rd.tasks.service.rest.RestSecurityConfig.WEB_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiTest {

	private final TestRestTemplate restTemplate;
	private final int port;

	private final TaskDefinitionModel taskDefinitionModel = new TaskDefinitionModel(100L, "Task1", "t1");
	private final TaskOperationModel taskOperationModel = new TaskOperationModel(null, 1L, 10000L, LocalDateTime.of(2023, 1, 1, 1, 1, 1));

	@Autowired
	public RestApiTest(TestRestTemplate restTemplate, @Value(value="${local.server.port}") int port) {
		this.restTemplate = restTemplate.withBasicAuth(WEB_USER, WEB_SECRET);
		this.port = port;
	}

	@Test
	public void testContextLoading() throws Exception {
		assertNotNull(restTemplate);
		assertTrue(port > 0);
	}

	@Test
	public void testAbout() throws Exception {
		assertThat(restTemplate.getForObject(url(), String.class)).contains("Tasks service");
	}

	@Test
	public void testGetAllTaskDefinitions() throws Exception {
		String returned = restTemplate.getForObject(url() + "/api/v1/task", String.class);
		assertThat(returned).contains("Cleaning");
	}

	@Test
	public void testCreateAndDeleteTaskDefinition() throws Exception {
		TaskDefinitionModel returned = restTemplate.postForObject(url() + "/api/v1/task", taskDefinitionModel, TaskDefinitionModel.class);
		assertEquals("Task1", returned.getName());
		assertEquals("t1", returned.getCode());
		restTemplate.delete(url() + "/api/v1/task/" + returned.getId());
	}

	@Test
	public void testCreateAndDeleteTaskOperation() throws Exception {
		TaskOperationModel returned = restTemplate.postForObject(url() + "/api/v1/task/operation", taskOperationModel, TaskOperationModel.class);
		assertEquals(taskOperationModel.getStartTime(), returned.getStartTime());
		restTemplate.delete(url() + "/api/v1/task/operation" + returned.getId());
	}

	@Test
	public void testCreateInvalidTaskOperation() throws Exception {
		assertThrows(Exception.class, () -> {
			taskOperationModel.setDuration(-1L);
			restTemplate.postForObject(url() + "/api/v1/task/operation", taskOperationModel, TaskOperationModel.class);
		});
	}

	@Test
	public void testTaskOperationQuery() throws Exception {
		TaskOperationQueryParamModel param = new TaskOperationQueryParamModel();
		param.setStartTime(DatabaseConfiguration.THE_DAY.minusDays(1));
		param.setEndTime(DatabaseConfiguration.THE_DAY.plusDays(2));
		param.setTaskDefinitionId(1L);

		Map<String, ?> paramsMap = param.toMap();
		TaskOperationQueryResultModel result = restTemplate.getForObject(url() + "/api/v1/task/operation/query?startTime={startTime}&endTime={endTime}&taskDefinitionId={taskDefinitionId}",
				TaskOperationQueryResultModel.class, paramsMap);
		assertEquals(60420L, result.getResultValue().longValue());
	}

	protected String url() {
		return "http://localhost:" + port + "/";
	}
}
