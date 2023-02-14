package net.rd.tasks.service.jpa.repository;

import net.rd.tasks.service.jpa.entity.TaskDefinitionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskDefinitionRepository extends CrudRepository<TaskDefinitionEntity, Long> {

}
