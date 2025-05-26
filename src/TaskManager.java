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

    public Task getTaskById(Integer taskId) {
        return tasks.get(taskId);
    }

    public Sub getSubById(Integer subId) {
        return subs.get(subId);
    }

    public Epic getEpicById(Integer epicId) {
        return epics.get(epicId);
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
        Epic epic = epics.get(sub.getEpicId());
        if (epic == null) {
            return null;
        }
        sub.setId(getNextId());
        subs.put(sub.getId(), sub);
        epic.addSubToSubTasks(sub.getId());
        updateEpicStatus(epic);
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

    public Task updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    public Epic updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic oldEpic = epics.get(epic.getId());
            oldEpic.setTaskName(epic.taskName);
            oldEpic.setTaskDescription(epic.taskDescription);
            return epic;
        }
        return null;
    }

    public Sub updateSub(Sub newSub) {
        Sub oldSub = subs.get(newSub.getId());
        if (oldSub == null || newSub == null) {
            return null;
        }
        if (!oldSub.getEpicId().equals(newSub.getEpicId())) {
            return null;
        }
        subs.put(newSub.getId(), newSub);

        Epic epic = epics.get(newSub.getEpicId());
        updateEpicStatus(epic);
        return newSub;
    }

    public void deleteSub(Integer subId) {
        Sub sub = subs.get(subId);
        if (sub != null) {
            Epic epic = epics.get(sub.getEpicId());
            epic.deleteSubFromEpic(subId);
            updateEpicStatus(epic);
            subs.remove(subId);
        }
    }

    public void deleteEpic(Integer epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            ArrayList<Integer> subsFromEpic = epic.getSubTasksId();
            for (Integer subsFrEp : subsFromEpic) {
                subs.remove(subsFrEp);
            }
            epics.remove(epicId);
        }
    }

    public ArrayList<Sub> getSubByEpicId(Integer epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<Sub> subsFromEpic = new ArrayList<>();
        if (epic != null) {
            for (Sub sub : subs.values()) {
                if (sub.getEpicId().equals(epic.getId())) {
                    subsFromEpic.add(sub);
                }
            }
        }
        return subsFromEpic;
    }

    public void deleteTask(Integer taskId) {
        tasks.remove(taskId);
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllSubs() {
        for (Sub sub : new ArrayList<>(subs.values())) {
            deleteSub(sub.getId());
        }
    }

    public void removeAllEpics() {
        for (Epic epic : new ArrayList<>(epics.values())) {
            deleteEpic(epic.getId());
        }
    }
}

