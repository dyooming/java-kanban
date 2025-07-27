package Managers;
import Task.*;

import java.util.List;
import java.util.Map;

public interface TaskManager {

    Task getTaskById(Integer id);

    Sub getSubById(Integer id);

    Epic getEpicById(Integer id);

    Task createTask(Task task);

    Epic createEpic(Epic epic);

    Sub createSub(Sub sub);

    List<Task> getAllTasksToList(Map<Integer, Task> tasks);

    List<Epic> getAllEpicsToList(Map<Integer, Epic> epics);

    List<Sub> getAllSubsToList(Map<Integer, Sub> subs);

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Sub updateSub(Sub newSub);

    void deleteSub(Integer subId);

    void deleteEpic(Integer epicId);

    List<Sub> getSubByEpicId(Integer epicId);

    void deleteTask(Integer taskId);

    void removeAllTasks();

    void removeAllSubs();

    void removeAllEpics();

    List<Task> getHistory();
}
