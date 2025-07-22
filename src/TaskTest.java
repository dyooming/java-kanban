import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TaskTest {

    public static InMemoryTaskManager inMemoryTaskManager;
    public static InMemoryHistoryManager inMemoryHistoryManager;

    @BeforeEach
    public void managerCreator() {
        inMemoryTaskManager = new InMemoryTaskManager();
        inMemoryHistoryManager = new InMemoryHistoryManager();
    }

    @Test
    void getId() {
        Task task1 = new Task("Test Name", "Test Description", TaskStatus.NEW);
        inMemoryTaskManager.createTask(task1);
        Task task2 = inMemoryTaskManager.getTaskById(task1.getId());
        inMemoryTaskManager.createTask(task2);

        assertSame(inMemoryTaskManager.getTaskById(task1.getId()), inMemoryTaskManager.getTaskById(task2.getId()), "Задачи не совпадают!");
    }

    @Test
    void getIdFromSameExtenders() {
        Epic epic1 = new Epic("Test Name", "Test Description");
        inMemoryTaskManager.createEpic(epic1);
        Epic epic2 = inMemoryTaskManager.getEpicById(epic1.getId());
        inMemoryTaskManager.createEpic(epic2);


        Sub sub1 = new Sub("Test Name", "Test Description", TaskStatus.NEW, 1);
        inMemoryTaskManager.createSub(sub1);
        Sub sub2 = inMemoryTaskManager.getSubById(sub1.getId());
        inMemoryTaskManager.createSub(sub2);

        assertSame(inMemoryTaskManager.getEpicById(epic1.getId()), inMemoryTaskManager.getEpicById(epic2.getId()), "Конфликт id");
        assertSame(inMemoryTaskManager.getSubById(sub1.getId()), inMemoryTaskManager.getSubById(sub2.getId()), "Конфликт id");
    }

    @Test
    void setEpicAsSubToSameEpic() {
        Epic epic = new Epic("Test Name", "Test Description");
        inMemoryTaskManager.createEpic(epic);
        Sub sub = new Sub(epic.getId(), "Test Name", "Test Description", TaskStatus.DONE, epic.getId());
        inMemoryTaskManager.createSub(sub);
        Sub existSub = inMemoryTaskManager.getSubById(sub.getId());

        assertNull(existSub, "Эпик добавился в свой же сабтаск");
    }

    @Test
    void setSubAsEpic() {
        Sub sub = new Sub("Test Name", "Test Description", TaskStatus.NEW, 1);
        inMemoryTaskManager.createSub(sub);

        assertNull(inMemoryTaskManager.getEpicById(sub.id));
    }

    @Test
    void managerTest() {
        assertNotNull(Managers.getDefault(), "Экземпляр не проинициализирован");
        assertNotNull(Managers.getDefaultHistory(), "Экземпляр не проинициализирован");
    }

    @Test
    void creatingAndFindingTaskTest() {
        Task task = new Task("Test Name", "Test Description", TaskStatus.NEW);
        inMemoryTaskManager.createTask(task);
        Epic epic = new Epic("Test Name", "Test Description");
        inMemoryTaskManager.createEpic(epic);
        Sub sub = new Sub("Test Name", "Test Description", TaskStatus.NEW, epic.getId());
        inMemoryTaskManager.createSub(sub);

        // сразу проверяем и создание таски и ее поиск
        assertNotNull(inMemoryTaskManager.getTaskById(task.getId()), "Таск не найден");
        assertNotNull(inMemoryTaskManager.getEpicById(epic.getId()), " Эпик не найден");
        assertNotNull(inMemoryTaskManager.getSubById(sub.getId()), "Сабтаск не найден");
    }

    @Test
    void validatingFieldsWhenCreatingTaskAndAdding() {
        Task task = new Task("Test Name", "Test Description", TaskStatus.NEW);
        inMemoryTaskManager.createTask(task);

        Task taskTest = inMemoryTaskManager.getTasks().get(task.getId());

        assertEquals(task.getId(), taskTest.getId(), "Не совпадают id");
        assertEquals(task.getTaskName(), taskTest.getTaskName(), "Не совпадают имена задач");
        assertEquals(task.getTaskDescription(), taskTest.getTaskDescription(), "Не совпадают описания задач");
        assertEquals(task.getTaskStatus(), taskTest.getTaskStatus(), "Не совпадают статусы задач");
    }

    @Test
    void historyManagerVersionEqualsTest() {
        Task task = new Task("Test Name", "Test Description", TaskStatus.NEW);
        inMemoryTaskManager.createTask(task);

        Task task1 = inMemoryTaskManager.getTaskById(task.id);
        // Вызываем getTaskById для добавления в историю задач
        Task taskFromHistory = inMemoryHistoryManager.getTaskFromHistory(task1.id);
        // У task1 id == 1, по этому же id в history ничего нет, так как он записался под id == 0

        assertEquals(task1.getId(), taskFromHistory.getId(), "Не совпадают id");
        assertEquals(task1.getTaskName(), taskFromHistory.getTaskName(), "Не совпадают имена задач");
        assertEquals(task1.getTaskDescription(), taskFromHistory.getTaskDescription(), "Не совпадают описания задач");
        assertEquals(task1.getTaskStatus(), taskFromHistory.getTaskStatus(), "Не совпадают статусы задач");
    }
}
