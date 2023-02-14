package net.rd.tasks.service.jpa;

import net.rd.tasks.service.jpa.repository.TaskOperationRepository;
import net.rd.tasks.service.jpa.repository.TaskDefinitionRepository;
import net.rd.tasks.service.jpa.entity.TaskDefinitionEntity;
import net.rd.tasks.service.jpa.entity.TaskOperationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DatabaseConfiguration {

    /**
     * The day with an example data
     * 2023-01-01
     */
    public static final LocalDateTime THE_DAY = LocalDateTime.of(2023, 2, 1, 0, 0, 0);

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public CommandLineRunner initialData(TaskDefinitionRepository defsRepository, TaskOperationRepository opsRepository) {
        return (args) -> {
            log.info("Data initialization");
            TaskDefinitionEntity taskClean = defsRepository.save(new TaskDefinitionEntity(1L, "Cleaning", "cl"));
            TaskDefinitionEntity taskCf1 = defsRepository.save(new TaskDefinitionEntity(2L, "Making cappuccino", "cf-1"));
            TaskDefinitionEntity taskCf2 = defsRepository.save(new TaskDefinitionEntity(3L, "Making latte", "cf-2"));
            TaskDefinitionEntity taskCf3 = defsRepository.save(new TaskDefinitionEntity(4L, "Making americano", "cf-3"));

            //Cleaning hourly, sometimes delayed
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(0), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(3), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 58000L, THE_DAY.plusHours(12).plusMinutes(2), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 62060L, THE_DAY.plusHours(13).plusMinutes(0), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 61008L, THE_DAY.plusHours(14).plusMinutes(0), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 63070L, THE_DAY.plusHours(15).plusMinutes(0), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 60045L, THE_DAY.plusHours(16).plusMinutes(1), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 59080L, THE_DAY.plusHours(17).plusMinutes(0), taskClean));

            //People making coffee
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(1), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(21), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(48), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(55), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(59), taskCf3));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(4), taskCf3));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(39), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(44), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(58), taskCf3));
            opsRepository.save(new TaskOperationEntity(null, 58000L, THE_DAY.plusHours(12).plusMinutes(3), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 58000L, THE_DAY.plusHours(12).plusMinutes(49), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 62060L, THE_DAY.plusHours(13).plusMinutes(16), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 60045L, THE_DAY.plusHours(16).plusMinutes(37), taskCf3));
            opsRepository.save(new TaskOperationEntity(null, 59080L, THE_DAY.plusHours(17).plusMinutes(2), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 59080L, THE_DAY.plusHours(17).plusMinutes(4), taskCf3));

            Iterable<TaskDefinitionEntity> definitionEntities = defsRepository.findAll();
            for (TaskDefinitionEntity entity : definitionEntities) {
                log.info("" + entity);
            }

            Iterable<TaskOperationEntity> opsEntities = opsRepository.findAll();
            for (TaskOperationEntity entity : opsEntities) {
                log.info("" + entity);
            }
        };
    }
}
