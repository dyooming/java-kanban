import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    private ArrayList<Integer> subTasksId;


    public Epic(String taskName, String taskDescription) {
        super(taskName, taskDescription, TaskStatus.NEW);
        this.subTasksId = new ArrayList<>();

    }
    public Epic(Integer id, String taskName, String taskDescription) {
        super(id,taskName, taskDescription, TaskStatus.NEW);
        this.subTasksId = new ArrayList<>();
    }
//
    public ArrayList<Integer> getSubTasksId() {
        return subTasksId;
    }

    public void deleteSubFromEpic(Integer subId){
        subTasksId.remove(subId);
    }

    public void clearAllSubTasks(){
        subTasksId.clear();
    }

    public void addSubToSubTasks(Integer subId){
        subTasksId.add(subId);
    }

    @Override
    public String toString() {
        ArrayList<Integer> subTaskIds = new ArrayList<>();
        for (Integer subTaskId : subTasksId) {
            subTaskIds.add(subTaskId);
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

