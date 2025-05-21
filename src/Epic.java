import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Sub> subTasks;


    public Epic(String taskName, String taskDescription) {
        super(taskName, taskDescription, TaskStatus.NEW);
        this.subTasks = new ArrayList<>();
    }

    public ArrayList<Sub> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<Sub> subTasks) {
        this.subTasks = subTasks;
    }


    @Override
    public String toString() {
        ArrayList<Integer> subTaskIds = new ArrayList<>();
        for (Sub subTask : subTasks) {
            subTaskIds.add(subTask.getId());
        }
        return "Epic{" +
                "id=" + getId() +
                ", taskName='" + getTaskName() + '\'' +
                ", taskDescription='" + getTaskDescription() + '\'' +
                ", taskStatus=" + getTaskStatus() + '\'' +
                ", subTasksId=" + subTaskIds + '\'' +
                '}';
    }
}
