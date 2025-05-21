
public class Sub extends Task{
    private Integer epicId;

    public Sub(String taskName, String taskDescription, TaskStatus taskStatus, Integer epicId) {
        super(taskName, taskDescription, taskStatus);
        this.epicId = epicId;
    }

    public Sub( Integer id, String taskName, String taskDescription, TaskStatus taskStatus, Integer epicId) {
        super(id, taskName, taskDescription, taskStatus);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }


    @Override
    public String toString() {
        return "Sub{" +
                "id=" + getId() +
                ", taskName='" + getTaskName() + '\'' +
                ", taskDescription='" + getTaskDescription() + '\'' +
                ", taskStatus=" + getTaskStatus() + '\'' +
                ", epicId=" + epicId +
                '}';
    }
}
