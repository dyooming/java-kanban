import java.util.List;

public interface HistoryManager {

    void addTask(Task task);

    void printHistory();

    void updateHistory();

    List<Task> getHistory();
}
