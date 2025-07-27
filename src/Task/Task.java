package Task;

import java.util.ArrayList;
import java.util.Objects;

public class Task {
    Integer id;
    String taskName;
    String taskDescription;
    TaskStatus taskStatus;

    private ArrayList<Integer> subTasksId;

    public Task(Integer id, String taskName, String taskDescription, TaskStatus taskStatus) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }

    public Task(String taskName, String taskDescription, TaskStatus taskStatus) {
        // вызов метода утилитарного класса по созданию id
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(taskName, task.taskName) && Objects.equals(taskDescription, task.taskDescription) && taskStatus == task.taskStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, taskDescription, taskStatus);
    }

    @Override
    public String toString() {
        return "Task.Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                '}';
    }

    public void addSubToSubTasks(Integer subId){
        subTasksId.add(subId);
    }
}


