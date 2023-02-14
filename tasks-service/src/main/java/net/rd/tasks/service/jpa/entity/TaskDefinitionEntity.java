package net.rd.tasks.service.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "task_definition")
public class TaskDefinitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=1000, nullable = false)
    private String name;
    @Column(length=255)
    private String code;

    @OneToMany(mappedBy = "taskDefinition", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<TaskOperationEntity> taskOperations = new HashSet<>();

    public TaskDefinitionEntity() {
        // Do-nothing constructor
    }

    public TaskDefinitionEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public TaskDefinitionEntity(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Set<TaskOperationEntity> getTaskOperations() {
        return taskOperations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDefinitionEntity that = (TaskDefinitionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskDefinitionEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("code='" + code + "'")
                .toString();
    }
}
