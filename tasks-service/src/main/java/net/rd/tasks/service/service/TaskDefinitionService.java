package net.rd.tasks.service.service;

import net.rd.tasks.service.exception.TaskDefinitionEntityNotFoundException;
import net.rd.tasks.service.exception.TaskDefinitionInvalidException;
import net.rd.tasks.service.transformer.ModelEntityTransformer;
import net.rd.tasks.service.validation.InputModelValidator;
import net.rd.tasks.service.jpa.entity.TaskDefinitionEntity;
import net.rd.tasks.service.jpa.repository.TaskDefinitionRepository;
import net.rd.tasks.service.model.TaskDefinitionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskDefinitionService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private TaskDefinitionRepository taskDefinitionRepository;

    public TaskDefinitionService(TaskDefinitionRepository taskDefinitionRepository) {
        this.taskDefinitionRepository = taskDefinitionRepository;
    }

    public Iterable<TaskDefinitionModel> getAllTaskDefinitions() {
        log.info("Get all task-definitions");

        Iterable<TaskDefinitionEntity> entitiesList = taskDefinitionRepository.findAll();
        List<TaskDefinitionModel> modelsList = new ArrayList<>();
        for (TaskDefinitionEntity taskDefinitionEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(taskDefinitionEntity));
        }

        return modelsList;
    }

    public TaskDefinitionEntity createTaskDefinition(TaskDefinitionModel taskDefinitionModel) {
        log.info("Creating task-definition " + taskDefinitionModel);

        if(!InputModelValidator.valid(taskDefinitionModel))
            throw new TaskDefinitionInvalidException(taskDefinitionModel);

        TaskDefinitionEntity taskDefinitionEntity = ModelEntityTransformer.modelToEntity(taskDefinitionModel);

        return taskDefinitionRepository.save(taskDefinitionEntity);
    }

    public TaskDefinitionModel getOneTaskDefinition(Long id) {
        TaskDefinitionEntity taskDefinitionEntity = taskDefinitionRepository
                .findById(id)
                .orElseThrow(() -> new TaskDefinitionEntityNotFoundException(id));
        TaskDefinitionModel taskDefinitionModel = ModelEntityTransformer.entityToModel(taskDefinitionEntity);

        return taskDefinitionModel;
    }

    public TaskDefinitionModel updateOrCreateTaskDefinition(TaskDefinitionModel taskDefinition, Long id) {
        log.info("Updating task definition " + id);

        return taskDefinitionRepository.findById(id).map(s -> {
            TaskDefinitionEntity taskDefinitionEntity = ModelEntityTransformer.modelToEntity(taskDefinition);
            TaskDefinitionEntity saved = taskDefinitionRepository.save(taskDefinitionEntity);
            TaskDefinitionModel taskDefinitionModel = ModelEntityTransformer.entityToModel(saved);
            return taskDefinitionModel;
        }).orElseGet(() -> {
            taskDefinition.setId(id);
            TaskDefinitionEntity taskDefinitionEntity = ModelEntityTransformer.modelToEntity(taskDefinition);
            TaskDefinitionEntity saved = taskDefinitionRepository.save(taskDefinitionEntity);
            TaskDefinitionModel taskDefinitionModel = ModelEntityTransformer.entityToModel(saved);
            return taskDefinitionModel;
        });
    }

    public void deleteTaskDefinition(Long id) {
        log.info("Deleting task definition " + id);
        if(!taskDefinitionRepository.existsById(id))
            throw new TaskDefinitionEntityNotFoundException(id);

        taskDefinitionRepository.deleteById(id);
    }
}
