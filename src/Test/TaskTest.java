package Test;

import Managers.*;
import Task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TaskTest {

    HistoryManager historyManager;
    TaskManager taskManager;


    @BeforeEach
    public void managerCreator() {
        historyManager = Managers.getDefaultHistory();
        taskManager = Managers.getDefault();
    }

    @Test
    void getId() {
        Task task1 = new Task("Test Name1", "Test Description", TaskStatus.NEW);
        taskManager.createTask(task1);
        Task task2 = taskManager.getTaskById(task1.getId());
        taskManager.createTask(task2);

        assertSame(taskManager.getTaskById(task1.getId()), taskManager.getTaskById(task2.getId()), "Задачи не совпадают!");
    }

    @Test
    void getIdFromSameExtenders() {
        Epic epic1 = new Epic("Test Name2", "Test Description");
        taskManager.createEpic(epic1);
        Epic epic2 = taskManager.getEpicById(epic1.getId());
        taskManager.createEpic(epic2);


        Sub sub1 = new Sub("Test Name3", "Test Description", TaskStatus.NEW, epic1.getId());
        taskManager.createSub(sub1);
        Sub sub2 = taskManager.getSubById(sub1.getId());
        taskManager.createSub(sub2);

        assertSame(taskManager.getEpicById(epic1.getId()), taskManager.getEpicById(epic2.getId()), "Конфликт id");
        assertSame(taskManager.getSubById(sub1.getId()), taskManager.getSubById(sub2.getId()), "Конфликт id");
    }

    @Test
    void setEpicAsSubToSameEpic() {
        Epic epic = new Epic("Test Name4", "Test Description");
        taskManager.createEpic(epic);
        Sub sub = new Sub(epic.getId(), "Test Name5", "Test Description", TaskStatus.DONE, epic.getId());
        taskManager.createSub(sub);
        Sub existSub = taskManager.getSubById(sub.getId());

        assertNull(existSub, "Эпик добавился в свой же сабтаск");
    }

    @Test
    void setSubAsEpic() {
        Sub sub = new Sub("Test Name6", "Test Description", TaskStatus.NEW, 1);
        taskManager.createSub(sub);

        assertNull(taskManager.getEpicById(sub.getId()));
    }

    @Test
    void managerTest() {
        assertNotNull(Managers.getDefault(), "Экземпляр не проинициализирован");
        assertNotNull(Managers.getDefaultHistory(), "Экземпляр не проинициализирован");
    }

    @Test
    void creatingAndFindingTaskTest() {
        Task task = new Task("Test Name7", "Test Description", TaskStatus.NEW);
        taskManager.createTask(task);
        Epic epic = new Epic("Test Name8", "Test Description");
        taskManager.createEpic(epic);
        Sub sub = new Sub("Test Name9", "Test Description", TaskStatus.NEW, epic.getId());
        taskManager.createSub(sub);

        // сразу проверяем и создание таски и ее поиск
        assertNotNull(taskManager.getTaskById(task.getId()), "Таск не найден");
        assertNotNull(taskManager.getEpicById(epic.getId()), " Эпик не найден");
        assertNotNull(taskManager.getSubById(sub.getId()), "Сабтаск не найден");
    }

    @Test
    void validatingFieldsWhenCreatingTaskAndAdding() {
        Task task = new Task("Test Name10", "Test Description", TaskStatus.NEW);
        taskManager.createTask(task);

        Task taskTest = taskManager.getTaskById(task.getId());

        assertEquals(task.getId(), taskTest.getId(), "Не совпадают id");
        assertEquals(task.getTaskName(), taskTest.getTaskName(), "Не совпадают имена задач");
        assertEquals(task.getTaskDescription(), taskTest.getTaskDescription(), "Не совпадают описания задач");
        assertEquals(task.getTaskStatus(), taskTest.getTaskStatus(), "Не совпадают статусы задач");
    }

    @Test
    void historyManagerVersionEqualsTest() {

        Task task = new Task("Test Name11", "Test Description", TaskStatus.NEW);
        taskManager.createTask(task);

        List<Task> historyCopy = historyManager.getHistory();

        Task task1 = taskManager.getTaskById(task.getId());
        Task taskFromHistory = historyCopy.get(task1.getId());

        assertEquals(task1.getId(), taskFromHistory.getId(), "Не совпадают id");
        assertEquals(task1.getTaskName(), taskFromHistory.getTaskName(), "Не совпадают имена задач");
        assertEquals(task1.getTaskDescription(), taskFromHistory.getTaskDescription(), "Не совпадают описания задач");
        assertEquals(task1.getTaskStatus(), taskFromHistory.getTaskStatus(), "Не совпадают статусы задач");
    }
}

