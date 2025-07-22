import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TaskManager {

    Task getTaskById(Integer id);

    Sub getSubById(Integer id);

    Epic getEpicById(Integer id);

    Task createTask(Task task);

    Epic createEpic(Epic epic);

    Sub createSub(Sub sub);

    ArrayList<Task> getAllTasksToList(HashMap<Integer, Task> tasks);

    ArrayList<Epic> getAllEpicsToList(HashMap<Integer, Epic> epics);

    ArrayList<Sub> getAllSubsToList(HashMap<Integer, Sub> subs);

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Sub updateSub(Sub newSub);

    void deleteSub(Integer subId);

    void deleteEpic(Integer epicId);

    ArrayList<Sub> getSubByEpicId(Integer epicId);

    void deleteTask(Integer taskId);

    void removeAllTasks();

    void removeAllSubs();

    void removeAllEpics();

    Epic updateEpicStatus(Epic epic);

    HashMap<Integer, Task> getTasks();

    HashMap<Integer, Sub> getSubs();

    HashMap<Integer, Epic> getEpics();
}
