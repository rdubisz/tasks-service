package net.rd.tasks.service.service;

import net.rd.tasks.service.exception.TaskDefinitionEntityNotFoundException;
import net.rd.tasks.service.exception.TaskOperationEntityNotFoundException;
import net.rd.tasks.service.exception.TaskOperationInvalidException;
import net.rd.tasks.service.exception.TaskOperationQueryParamInvalidException;
import net.rd.tasks.service.transformer.ModelEntityTransformer;
import net.rd.tasks.service.validation.InputModelValidator;
import net.rd.tasks.service.jpa.entity.TaskOperationEntity;
import net.rd.tasks.service.jpa.repository.ReportingQueryRepository;
import net.rd.tasks.service.jpa.repository.TaskOperationRepository;
import net.rd.tasks.service.jpa.repository.TaskDefinitionRepository;
import net.rd.tasks.service.model.TaskOperationModel;
import net.rd.tasks.service.model.TaskOperationQueryParamModel;
import net.rd.tasks.service.model.TaskOperationQueryResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskOperationService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private TaskDefinitionRepository taskDefinitionRepository;
    private TaskOperationRepository taskOperationRepository;
    private ReportingQueryRepository reportingQueryRepository;

    public TaskOperationService(TaskDefinitionRepository taskDefinitionRepository, TaskOperationRepository taskOperationRepository, ReportingQueryRepository reportingQueryRepository) {
        this.taskDefinitionRepository = taskDefinitionRepository;
        this.taskOperationRepository = taskOperationRepository;
        this.reportingQueryRepository = reportingQueryRepository;
    }

    public Iterable<TaskOperationModel> getAllTaskOperations() {
        Iterable<TaskOperationEntity> entitiesList = taskOperationRepository.findAll();
        List<TaskOperationModel> modelsList = new ArrayList<>();
        for (TaskOperationEntity taskOperationEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(taskOperationEntity));
        }
        return modelsList;
    }

    public Iterable<TaskOperationModel> getTaskOperations(Long id) {
        log.info("Getting operations for task-definition " + id);
        List<TaskOperationEntity> entitiesList = taskOperationRepository.findByTaskDefinitionId(id);
        List<TaskOperationModel> modelsList = new ArrayList<>();
        for (TaskOperationEntity taskOperationEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(taskOperationEntity));
        }
        return modelsList;
    }

    @Transactional
    public TaskOperationModel createTaskOperation(TaskOperationModel taskOperationModel) {
        log.info("Creating task-operation " + taskOperationModel);

        if (!InputModelValidator.valid(taskOperationModel))
            throw new TaskOperationInvalidException(taskOperationModel);

        if(!taskDefinitionRepository.existsById(taskOperationModel.getTaskDefinitionId()))
            throw new TaskDefinitionEntityNotFoundException(taskOperationModel.getTaskDefinitionId());

        if (taskOperationModel.getStartTime() == null)
            taskOperationModel.setStartTime(LocalDateTime.now(ZoneId.of("UTC")));
        TaskOperationEntity taskOperationEntity = ModelEntityTransformer.modelToEntity(taskOperationModel);
        TaskOperationEntity saved = taskOperationRepository.save(taskOperationEntity);

        return ModelEntityTransformer.entityToModel(saved);
    }


    public TaskOperationModel updateOrCreateTaskOperation(TaskOperationModel taskOperation, Long id) {
        log.info("Updating task-operation " + taskOperation + " with id " + id);

        return taskOperationRepository.findById(id).map(r -> {
            r.setDuration(taskOperation.getDuration());
            TaskOperationEntity saved = taskOperationRepository.save(r);
            TaskOperationModel taskOperationModel = ModelEntityTransformer.entityToModel(saved);
            return taskOperationModel;
        }).orElseGet(() -> {
            TaskOperationEntity taskOperationEntity = ModelEntityTransformer.modelToEntity(taskOperation);
            taskOperationEntity.setId(id);
            TaskOperationEntity saved = taskOperationRepository.save(taskOperationEntity);
            TaskOperationModel taskOperationModel = ModelEntityTransformer.entityToModel(saved);
            return taskOperationModel;
        });
    }

    public TaskOperationModel getOneTaskOperation(Long id) {
        log.info("Getting operations for task-definition " + id);

        if (!taskDefinitionRepository.existsById(id))
            throw new TaskDefinitionEntityNotFoundException(id);

        TaskOperationEntity taskOperationEntity = taskOperationRepository.findById(id)
                .orElseThrow(() -> new TaskOperationEntityNotFoundException(id));
        TaskOperationModel taskOperationModel = ModelEntityTransformer.entityToModel(taskOperationEntity);
        return taskOperationModel;
    }

    public void deleteTaskOperation(Long id) {
        log.info("Deleting task-operation " + id);

        if (!taskOperationRepository.existsById(id))
            throw new TaskOperationEntityNotFoundException(id);

        taskOperationRepository.deleteById(id);
    }

    public TaskOperationQueryResultModel taskOperationsQuery(TaskOperationQueryParamModel param) {
        log.info("Querying " + param);

        if (!InputModelValidator.valid(param))
            throw new TaskOperationQueryParamInvalidException(param);

        Double result = reportingQueryRepository.taskOperationQuery(
                param.getStartTime(), param.getEndTime(), param.getTaskDefinitionId());

        TaskOperationQueryResultModel resultModel = new TaskOperationQueryResultModel(param);
        resultModel.setResultValue(result);
        return resultModel;
    }
}
