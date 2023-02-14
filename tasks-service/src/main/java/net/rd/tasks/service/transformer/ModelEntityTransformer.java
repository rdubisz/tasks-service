package net.rd.tasks.service.transformer;

import net.rd.tasks.service.jpa.entity.TaskDefinitionEntity;
import net.rd.tasks.service.jpa.entity.TaskOperationEntity;
import net.rd.tasks.service.model.TaskDefinitionModel;
import net.rd.tasks.service.model.TaskOperationModel;
import org.springframework.beans.BeanUtils;

public class ModelEntityTransformer {

    public static TaskDefinitionEntity modelToEntity(TaskDefinitionModel model) {
        if(model != null) {
            TaskDefinitionEntity entity = new TaskDefinitionEntity();
            BeanUtils.copyProperties(model, entity);
            return entity;
        } else
            return null;
    }

    public static TaskDefinitionModel entityToModel(TaskDefinitionEntity entity) {
        if(entity != null) {
            TaskDefinitionModel model = new TaskDefinitionModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        } else
            return null;
    }

    public static TaskOperationEntity modelToEntity(TaskOperationModel model) {
        if(model != null) {
            TaskOperationEntity entity = new TaskOperationEntity();
            BeanUtils.copyProperties(model, entity);
            if(model.getTaskDefinitionId() != null) {
                TaskDefinitionEntity taskDefinitionEntity = new TaskDefinitionEntity();
                taskDefinitionEntity.setId(model.getTaskDefinitionId());
                entity.setTaskDefinitionEntity(taskDefinitionEntity);
            }
            return entity;
        } else
            return null;
    }

    public static TaskOperationModel entityToModel(TaskOperationEntity entity) {
        if(entity != null) {
            TaskOperationModel model = new TaskOperationModel();
            BeanUtils.copyProperties(entity, model);
            if(entity.getTaskDefinitionEntity() != null)
                model.setTaskDefinitionId(entity.getTaskDefinitionEntity().getId());
            return model;
        } else
            return null;
    }
}
