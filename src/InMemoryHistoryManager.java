import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    private List<Task> history = new ArrayList<>();


    @Override
    public void printHistory (){
        System.out.println("\n" + "Последние 10 просмотренных задач: ");
        for (Task task : history){
            System.out.println(task);
        }
    }

    public Task getTaskFromHistory(Integer id){
        for (Task task : history){
            if (task.getId() == id){
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }

    @Override
    public void addTask(Task task){
        history.add(task);
    }

    @Override
    public void updateHistory(){
        if (history.size() > 10){
            history.removeFirst();
        }
    }
}
