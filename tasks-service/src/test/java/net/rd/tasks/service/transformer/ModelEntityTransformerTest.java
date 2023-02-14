package net.rd.tasks.service.transformer;

import net.rd.tasks.service.jpa.DatabaseConfiguration;
import net.rd.tasks.service.jpa.entity.TaskDefinitionEntity;
import net.rd.tasks.service.jpa.entity.TaskOperationEntity;
import net.rd.tasks.service.model.TaskDefinitionModel;
import net.rd.tasks.service.model.TaskOperationModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ModelEntityTransformerTest {

    private final TaskDefinitionModel taskDefinitionModel = new TaskDefinitionModel(1L, "S1", "SN:1");
    private final TaskDefinitionEntity taskDefinitionEntity = new TaskDefinitionEntity(1L, "S1", "SN:1");
    private final TaskOperationModel taskOperationModel = new TaskOperationModel(1L, 1L, 1L, LocalDateTime.of(2023, 1, 1, 1, 1, 1));
    private final TaskOperationEntity taskOperationEntity = new TaskOperationEntity(1L, 1L, DatabaseConfiguration.THE_DAY, taskDefinitionEntity);


    @Test
    public void modelToEntityTaskDefinition() {
        Assertions.assertEquals(taskDefinitionModel, ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity(taskDefinitionModel)));
        assertNull(ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity((TaskDefinitionModel) null)));
    }

    @Test
    public void entityToModelTaskDefinition() {
        Assertions.assertEquals(taskDefinitionEntity, ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel(taskDefinitionEntity)));
        assertNull(ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel((TaskDefinitionEntity) null)));
    }

    @Test
    public void testModelToEntityTaskOperation() {
        Assertions.assertEquals(taskOperationModel, ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity(taskOperationModel)));
        Assertions.assertEquals(1L, ModelEntityTransformer.modelToEntity(taskOperationModel).getTaskDefinitionEntity().getId());
        assertNull(ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity((TaskOperationModel) null)));

        taskOperationModel.setTaskDefinitionId(null);
        assertNull(ModelEntityTransformer.modelToEntity(taskOperationModel).getTaskDefinitionEntity());
    }

    @Test
    public void testEntityToModelTaskOperation() {
        Assertions.assertEquals(taskOperationEntity, ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel(taskOperationEntity)));
        Assertions.assertEquals(1L, ModelEntityTransformer.entityToModel(taskOperationEntity).getTaskDefinitionId());
        assertNull(ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel((TaskOperationEntity) null)));

        taskOperationEntity.setTaskDefinitionEntity(null);
        assertNull(ModelEntityTransformer.entityToModel(taskOperationEntity).getTaskDefinitionId());
    }
}
