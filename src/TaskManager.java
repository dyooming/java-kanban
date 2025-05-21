import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    private int generatorId = 1;

    public int getNextId() {
        return generatorId++;
    }

    public Task createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Sub createSub(Epic epic, Sub sub){
        sub.setId(getNextId());
        tasks.put(sub.getId(), sub);
        epic.getSubTasks().add(sub);
        updateEpicStatus(epic);
        return sub;
    }

    public Epic updateEpicStatus(Epic epic) {
        boolean isTaskStatusNew = true;
        boolean isTaskStatusDone = true;

        if (epic.getSubTasks().isEmpty()){
            epic.setTaskStatus(TaskStatus.NEW);
            return epic;
        }

        for (Sub sub : epic.getSubTasks()){
            if(sub.getTaskStatus() != TaskStatus.NEW){
                isTaskStatusNew = false;
            }
            if(sub.getTaskStatus() != TaskStatus.DONE){
                isTaskStatusDone = false;
            }
            if (sub.getTaskStatus() == TaskStatus.IN_PROGRESS){
                epic.setTaskStatus(TaskStatus.IN_PROGRESS);
                return epic;
            }
        }

        if(isTaskStatusNew){
            epic.setTaskStatus(TaskStatus.NEW);
        } else if (isTaskStatusDone) {
            epic.setTaskStatus(TaskStatus.DONE);
        } else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
        return epic;
    }

    public Task updateTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public Sub updateSub(Epic epic, Sub newSub, Sub oldSub) {
        epic.getSubTasks().remove(oldSub);
        tasks.remove(oldSub.getId());
        epic.getSubTasks().add(newSub);
        tasks.put(newSub.getId(), newSub);
        updateEpicStatus(epic);
        return newSub;
    }

    public void clearAllTasks() {
        tasks.clear();
    }

    public void printAllTasks() {
        System.out.println(tasks);
    }

    public Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    public void deleteById(Integer id) {
        Task task = tasks.get(id);

        if (task instanceof Epic) {
            Epic epic = (Epic) task;

            for (Sub sub : epic.getSubTasks()) {
                tasks.remove(sub.getId());
            }
            epic.getSubTasks().clear();
        } else if (task instanceof Sub) {
            Sub sub = (Sub) task;
            Epic epic = (Epic) tasks.get(sub.getEpicId());

            if (epic != null) {
                epic.getSubTasks().removeIf(s -> s.getId().equals(id));
            }
        }

        tasks.remove(id);
    }

    public Sub getSubByEpicId(Integer epicId, Integer subId) {
        Task task = tasks.get(epicId);

        if (!(task instanceof Epic)) {
            return null;
        }

        Epic epic = (Epic) task;

        for (Sub sub : epic.getSubTasks()) {
            if (sub.getId().equals(subId)) {
                return sub;
            }
        }
        return null;
    }
}

