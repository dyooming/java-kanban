
public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        System.out.println("-----Создать задачу-----");

        // Создаем две таски
        Task task1 = new Task("Сделать тренировочный план на неделю", "Дедлайн - пятница", TaskStatus.NEW);
        taskManager.createTask(task1);
        System.out.println(task1);
        Task task2 = new Task("Снять инструкцию по технике выполнения упражнений", "С двух камер", TaskStatus.IN_PROGRESS);
        taskManager.createTask(task2);
        System.out.println(task2);

        // Создаем эпик с 2 подзадачами
        Epic epic1 = new Epic("День ног", "Свободные веса");
        taskManager.createEpic(epic1);
        Sub sub1 = new Sub("Присед", "4 подхода по 10 повторений", TaskStatus.NEW, epic1.getId());
        taskManager.createSub(sub1);
        epic1.addSubToSubTasks(sub1.getId());
        Sub sub2 = new Sub("Выпады", "3 подхода по 12 повторений", TaskStatus.NEW, epic1.getId());
        taskManager.createSub(sub2);
        epic1.addSubToSubTasks(sub2.getId());

        System.out.println(epic1);
        System.out.println(sub1);
        System.out.println(sub2);


        // Создаем эпик с 1 подзадачей
        Epic epic2 = new Epic("Заминка", "Легкий темп");
        taskManager.createEpic(epic2);
        Sub sub3 = new Sub("Растяжка", "ТБС, задняя поверхность бедра", TaskStatus.NEW, epic2.getId());
        taskManager.createSub(sub3);
        epic2.addSubToSubTasks(sub3.getId());

        System.out.println(epic2);
        System.out.println(sub3);

        System.out.println("\n-----Выводим все задачи по категориям-----");
        System.out.println(taskManager.getAllTasks(taskManager.getTasks()));
        System.out.println(taskManager.getAllEpics(taskManager.getEpics()));
        System.out.println(taskManager.getAllSubs(taskManager.getSubs()));

        System.out.println("\n-----Обновить статус задач-----");
        Task updtTask1 = new Task(task1.getId(), "Переделать тренировочный план", "Добавить упражнения на спину", TaskStatus.IN_PROGRESS);
        taskManager.updateTask(updtTask1);
        System.out.println(updtTask1);
        Task updtTask2 = new Task(task2.getId(), "Налоги", "Разобраться с налоговой отчетностью", TaskStatus.DONE);
        taskManager.updateTask(updtTask2);
        System.out.println(updtTask2);

        Sub updtSub1 = new Sub(sub1.getId(), "Разгибание голени", "5 подходов по 15 повторений", TaskStatus.DONE, sub1.getEpicId());
        taskManager.updateSub(updtSub1);
        Sub updtSub2 = new Sub(sub2.getId(), "Платформа", "4 подхода по 12 повторений", TaskStatus.DONE, sub2.getEpicId());
        taskManager.updateSub(updtSub2);
        Sub updtSub3 = new Sub(sub3.getId(), "Кардио тренировка", "Эллипс - 60 минут", TaskStatus.IN_PROGRESS, sub3.getEpicId());
        taskManager.updateSub(updtSub3);

        System.out.println(epic1);
        System.out.println(updtSub1);
        System.out.println(updtSub2);
        System.out.println(epic2);
        System.out.println(updtSub3);

        System.out.println("\n-----Удалить две задачи и вывести все задачи-----");
        taskManager.deleteTask(2);
        taskManager.deleteEpic(6);

        System.out.println(taskManager.getSubByEpicId(3));

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubs());
    }
}
