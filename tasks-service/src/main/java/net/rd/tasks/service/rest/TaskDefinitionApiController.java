package net.rd.tasks.service.rest;

import net.rd.tasks.service.service.TaskDefinitionService;
import net.rd.tasks.service.jpa.entity.TaskDefinitionEntity;
import net.rd.tasks.service.model.TaskDefinitionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskDefinitionApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private TaskDefinitionService taskDefinitionService;

    public TaskDefinitionApiController(TaskDefinitionService taskDefinitionService) {
        this.taskDefinitionService = taskDefinitionService;
    }

    @GetMapping("/api/v1/task")
    public Iterable<TaskDefinitionModel> getAllTaskDefinitions() {
        return taskDefinitionService.getAllTaskDefinitions();
    }

    @PostMapping("/api/v1/task")
    public TaskDefinitionEntity createTaskDefinition(@RequestBody TaskDefinitionModel taskDefinitionModel) {
        return taskDefinitionService.createTaskDefinition(taskDefinitionModel);
    }

    @GetMapping("/api/v1/task/{id}")
    public TaskDefinitionModel getOneTaskDefinition(@PathVariable Long id) {
        return taskDefinitionService.getOneTaskDefinition(id);
    }

    @PutMapping("/api/v1/task/{id}")
    public TaskDefinitionModel updateOrCreateTaskDefinition(@RequestBody TaskDefinitionModel taskDefinitionModel, @PathVariable Long id) {
        return taskDefinitionService.updateOrCreateTaskDefinition(taskDefinitionModel, id);
    }

    @DeleteMapping("/api/v1/task/{id}")
    public void deleteTaskDefinition(@PathVariable Long id) {
        taskDefinitionService.deleteTaskDefinition(id);
    }
}
