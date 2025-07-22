import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class InMemoryTaskManager implements TaskManager {

    private  HashMap<Integer, Task> tasks = new HashMap<>();
    private  HashMap<Integer, Sub> subs = new HashMap<>();
    private  HashMap<Integer, Epic> epics = new HashMap<>();

    HistoryManager historyManager = Managers.getDefaultHistory();

    private int generatorId = 1;

    private int getNextId() {
        return generatorId++;
    }


    @Override
    public Task getTaskById(Integer id) {
        historyManager.addTask(tasks.get(id));
        historyManager.updateHistory();
        return tasks.get(id);

    }

    @Override
    public Sub getSubById(Integer id) {
        historyManager.addTask(subs.get(id));
        historyManager.updateHistory();
        return subs.get(id);
    }

    @Override
    public Epic getEpicById(Integer id) {
        historyManager.addTask(epics.get(id));
        historyManager.updateHistory();
        return epics.get(id);
    }

    @Override
    public Task createTask(Task task) {
        if(task.getId() == null){
            task.setId(getNextId());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        if(epic.getId() == null){
            epic.setId(getNextId());
        }
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public Sub createSub(Sub sub) {
//        if(!(sub instanceof  Sub)){
//            throw new IllegalArgumentException("Ожидается сабтаск");
//        }
        if(!epics.containsKey(sub.getEpicId())){
            return null;
        }
        Epic epic = epics.get(sub.getEpicId());

        if(Objects.equals(sub.getId(), epic.getId())){
            return null;
        }

        if(sub.getId() == null){
            sub.setId(getNextId());
        }
        subs.put(sub.getId(), sub);
        epic.addSubToSubTasks(sub.getId());
        updateEpicStatus(epic);
        return sub;
    }
    @Override
    public  HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    @Override
    public  HashMap<Integer, Sub> getSubs() {
        return subs;
    }

    @Override
    public  HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    @Override
    public ArrayList<Task> getAllTasksToList(HashMap<Integer, Task> tasks) {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values());
        return allTasks;
    }

    @Override
    public ArrayList<Epic> getAllEpicsToList(HashMap<Integer, Epic> epics) {
        ArrayList<Epic> allEpics = new ArrayList<>();
        allEpics.addAll(epics.values());
        return allEpics;
    }

    @Override
    public ArrayList<Sub> getAllSubsToList(HashMap<Integer, Sub> subs) {
        ArrayList<Sub> allSubs = new ArrayList<>();
        allSubs.addAll(subs.values());
        return allSubs;
    }

    public Epic updateEpicStatus(Epic epic) {
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

    @Override
    public Task updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return task;
        }
        return null;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic oldEpic = epics.get(epic.getId());
            oldEpic.setTaskName(epic.taskName);
            oldEpic.setTaskDescription(epic.taskDescription);
            return epic;
        }
        return null;
    }

    @Override
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

    @Override
    public void deleteSub(Integer subId) {
        Sub sub = subs.get(subId);
        if (sub != null) {
            Epic epic = epics.get(sub.getEpicId());
            epic.deleteSubFromEpic(subId);
            updateEpicStatus(epic);
            subs.remove(subId);
        }
    }

    @Override
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

    @Override
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

    @Override
    public void deleteTask(Integer taskId) {
        tasks.remove(taskId);
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void removeAllSubs() {
        for (Sub sub : new ArrayList<>(subs.values())) {
            deleteSub(sub.getId());
        }
    }

    @Override
    public void removeAllEpics() {
        for (Epic epic : new ArrayList<>(epics.values())) {
            deleteEpic(epic.getId());
        }
    }
}