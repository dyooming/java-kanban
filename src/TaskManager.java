import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Sub> subs = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();

    private int generatorId = 1;

    private int getNextId() {
        return generatorId++;
    }

    public Task createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Sub createSub(Sub sub) {
        sub.setId(getNextId());
        subs.put(sub.getId(), sub);
        return sub;
    }

    public ArrayList<Task> getAllTasks(HashMap<Integer, Task> tasks) {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values()); // добавляем все таски в новый список с тасками и возвращаем его
        return allTasks;
    }

    public ArrayList<Epic> getAllEpics(HashMap<Integer, Epic> epics) {
        ArrayList<Epic> allEpics = new ArrayList<>();
        allEpics.addAll(epics.values());
        return allEpics;
    }

    public ArrayList<Sub> getAllSubs(HashMap<Integer, Sub> subs) {
        ArrayList<Sub> allSubs = new ArrayList<>();
        allSubs.addAll(subs.values());
        return allSubs;
    }

    private Epic updateEpicStatus(Epic epic) {
        boolean isTaskStatusNew = true;
        boolean isTaskStatusDone = true;

        if (epic.getSubTasksId().isEmpty()) {
            epic.setTaskStatus(TaskStatus.NEW);
        }
        ArrayList<Sub> allSubsFromEpics = new ArrayList<>();
        for (Sub sub : subs.values()) {
            if (sub.getEpicId().equals(epic.getId())) {
                allSubsFromEpics.add(sub);
            }
        }

        for (Sub sub : allSubsFromEpics) {
            if (sub.getTaskStatus() != TaskStatus.NEW) {
                isTaskStatusNew = false;
            }

            if (sub.getTaskStatus() != TaskStatus.DONE) {
                isTaskStatusDone = false;
            }
            if (sub.getTaskStatus() == TaskStatus.IN_PROGRESS) {
                epic.setTaskStatus(TaskStatus.IN_PROGRESS);
                return epic;
            }
        }
        if (isTaskStatusNew) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else if (isTaskStatusDone) {
            epic.setTaskStatus(TaskStatus.DONE);
        } else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
        return epic;
    }

    public Task updateTask(Task newTask) {
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public Sub updateSub(Sub newSub) {
        if (newSub.getId() == null) {
            return null;
        }

        Sub oldSub = subs.get(newSub.getId());
        if (!oldSub.getEpicId().equals(newSub.getEpicId())) {
            return null;
        }

        subs.remove(oldSub.getId());
        subs.put(newSub.getId(), newSub);

        Epic epic = epics.get(newSub.getEpicId());
        ArrayList<Integer> subsFromEpic = epic.getSubTasksId();

        subsFromEpic.remove(oldSub.getId()); //можно через deleteSubFromEpic
        epic.addSubToSubTasks(newSub.getId());
        updateEpicStatus(epic);
        epics.put(epic.getId(), epic);
        return newSub;
    }

    public void deleteSub(Integer subId){
        Sub sub = subs.get(subId);
        Epic epic = epics.get(sub.getEpicId());
        ArrayList<Integer> subsFromEpic = epic.getSubTasksId();
        subsFromEpic.remove(subId);
        subs.remove(subId);
    }
    public void deleteEpic(Integer epicId){
        Epic epic = epics.get(epicId);
        ArrayList<Integer> subsFromEpic = epic.getSubTasksId();
        for (Integer subsFrEp : subsFromEpic){
            subs.remove(subsFrEp);
        }
        epics.remove(epicId);
    }

    public ArrayList<Sub> getSubByEpicId(Integer epicId){
        Epic epic = epics.get(epicId);
        ArrayList<Sub> subsFromEpic = new ArrayList<>();
        for (Sub sub : subs.values()){
            if(sub.getEpicId().equals(epic.getId())){
                subsFromEpic.add(sub);
            }
        }
        return subsFromEpic;
    }

    public void deleteTask(Integer taskId){
        tasks.remove(taskId);
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    public HashMap<Integer, Sub> getSubs() {
        return subs;
    }

    public void setSubs(HashMap<Integer, Sub> subs) {
        this.subs = subs;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public void setEpics(HashMap<Integer, Epic> epics) {
        this.epics = epics;
    }
}

