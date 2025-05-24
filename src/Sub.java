public class Sub extends Task {
    private final Integer epicId;

    public Sub(Integer id, String taskName, String taskDescription, TaskStatus taskStatus, Integer epicId) {
        super(id, taskName, taskDescription, taskStatus);
        this.epicId = epicId;
    }

    public Sub(String taskName, String taskDescription, TaskStatus taskStatus, Integer epicId) {
        super(taskName, taskDescription, taskStatus);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
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
