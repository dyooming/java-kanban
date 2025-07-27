package Managers;

import Task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>();

    @Override
    public List<Task> getHistory() {
        List<Task> historyCopy = history;
        return historyCopy;
    }

    public void printHistory() {
        System.out.println("\n" + "Последние 10 просмотренных задач: ");
        for (Task task : history) {
            System.out.println(task);
        }
    }

    @Override
    public void addTask(Task task) {
        if (task != null) {
            history.add(task);
        } else {
            System.out.println("Нельзя добавить в историю пустую задачу!");
        }
        if (history.size() > 10) {
            history.removeFirst();
        }
    }
}
