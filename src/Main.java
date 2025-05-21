
public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        System.out.println("-----1. Создать задачу-----");
        Task task1 = new Task("Погулять", "Выйти на прогулку с собакой", TaskStatus.NEW);
        taskManager.createTask(task1);

        Task task2 = new Task("Активный досуг", "Покататься на велосипеде", TaskStatus.IN_PROGRESS);
        taskManager.createTask(task2);

        //Создаем эпики

        Epic epic = new Epic("Чистый четверг", "Убраться дома");
        taskManager.createTask(epic);

        Sub sub = new Sub("В комнате", "Протереть пыль", TaskStatus.NEW, epic.getId());
        taskManager.createSub(epic, sub);

        Sub sub1 = new Sub("На кухне", "Помыть плиту", TaskStatus.NEW, epic.getId());
        taskManager.createSub(epic, sub1);

        Epic epic2 = new Epic("Сходить в зал", "Тренировка 1");
        taskManager.createTask(epic2);

        Sub sub2 = new Sub("Присед", "6 подходов по 10 повторений", TaskStatus.DONE, epic2.getId());
        taskManager.createSub(epic2, sub2);


        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getTaskById(3));
        System.out.println(taskManager.getTaskById(4));
        System.out.println(taskManager.getTaskById(5));
        System.out.println(taskManager.getTaskById(6));
        System.out.println(taskManager.getTaskById(7));

        System.out.println("\n-----2. Обновить статус задач-----");
        Task task1Updt = new Task(task1.getId(),task1.getTaskName(), task1.getTaskDescription(), TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task1Updt);
        Task task2Updt = new Task(task2.getId(),task1.getTaskName(), task2.getTaskDescription(), TaskStatus.DONE);
        taskManager.updateTask(task2Updt);


        Sub subUpdt = new Sub(sub.getId(),sub.getTaskName(), sub.getTaskDescription(), TaskStatus.IN_PROGRESS, sub.getEpicId());
        taskManager.updateSub(epic, subUpdt, sub);

        Sub subUpdt1 = new Sub(sub1.getId(), sub1.getTaskName(), sub1.getTaskDescription(), TaskStatus.IN_PROGRESS, sub1.getEpicId());
        taskManager.updateSub(epic, subUpdt1, sub1);

        Sub subUpdt2 = new Sub(sub2.getId(), sub2.getTaskName(), sub2.getTaskDescription(), TaskStatus.DONE, sub2.getEpicId());
        taskManager.updateSub(epic2, subUpdt2, sub2);

        taskManager.updateEpicStatus(epic);
        taskManager.updateEpicStatus(epic2);

        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getTaskById(3));
        System.out.println(taskManager.getTaskById(4));
        System.out.println(taskManager.getTaskById(5));
        System.out.println(taskManager.getTaskById(6));
        System.out.println(taskManager.getTaskById(7));


        System.out.println("\n-----3. Удалить две задачи и вывести все задачи-----");
        taskManager.deleteById(2);
        taskManager.deleteById(6);
        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getTaskById(3));
        System.out.println(taskManager.getTaskById(4));
        System.out.println(taskManager.getTaskById(5));
        System.out.println(taskManager.getTaskById(6));
        System.out.println(taskManager.getTaskById(7));

        System.out.println(taskManager.getSubByEpicId(3, 5));
    }
}
