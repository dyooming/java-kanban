
public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        System.out.println("-----Создать задачу-----");

        // Создаем две таски
        Task task1 = new Task("Сделать тренировочный план на неделю", "Дедлайн - пятница", TaskStatus.NEW);
        taskManager.createTask(task1);
        System.out.println(taskManager.getTaskById(1));
        Task task2 = new Task("Снять инструкцию по технике выполнения упражнений", "С двух камер", TaskStatus.IN_PROGRESS);
        taskManager.createTask(task2);
        System.out.println(taskManager.getTaskById(2));

        // Создаем эпик с 2 подзадачами
        Epic epic1 = new Epic("День ног", "Свободные веса");
        taskManager.createEpic(epic1);
        Sub sub1 = new Sub("Присед", "4 подхода по 10 повторений", TaskStatus.NEW, epic1.getId());
        taskManager.createSub(sub1);
        Sub sub2 = new Sub("Выпады", "3 подхода по 12 повторений", TaskStatus.NEW, epic1.getId());
        taskManager.createSub(sub2);

        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubById(4));
        System.out.println(taskManager.getSubById(5));


        // Создаем эпик с 1 подзадачей
        Epic epic2 = new Epic("Заминка", "Легкий темп");
        taskManager.createEpic(epic2);
        Sub sub3 = new Sub("Растяжка", "ТБС, задняя поверхность бедра", TaskStatus.NEW, epic2.getId());
        taskManager.createSub(sub3);

        System.out.println(taskManager.getEpicById(6));
        System.out.println(taskManager.getSubById(7));

        System.out.println("\n-----Обновить статус задач-----");
        Task updtTask1 = new Task(task1.getId(), "Переделать тренировочный план", "Добавить упражнения на спину", TaskStatus.IN_PROGRESS);
        taskManager.updateTask(updtTask1);
        Task updtTask2 = new Task(task2.getId(), "Налоги", "Разобраться с налоговой отчетностью", TaskStatus.DONE);
        taskManager.updateTask(updtTask2);

        Sub updtSub1 = new Sub(sub1.getId(), "Разгибание голени", "5 подходов по 15 повторений", TaskStatus.DONE, sub1.getEpicId());
        taskManager.updateSub(updtSub1);
        Sub updtSub2 = new Sub(sub2.getId(), "Платформа", "4 подхода по 12 повторений", TaskStatus.DONE, sub2.getEpicId());
        taskManager.updateSub(updtSub2);
        Sub updtSub3 = new Sub(sub3.getId(), "Кардио тренировка", "Эллипс - 60 минут", TaskStatus.IN_PROGRESS, sub3.getEpicId());
        taskManager.updateSub(updtSub3);

        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubById(4));
        System.out.println(taskManager.getSubById(5));
        System.out.println(taskManager.getEpicById(6));
        System.out.println(taskManager.getSubById(7));

        System.out.println("\n-----Удалить две задачи и вывести все задачи-----");
        taskManager.deleteTask(2);
        taskManager.deleteEpic(6);


        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubById(4));
        System.out.println(taskManager.getSubById(5));
        System.out.println(taskManager.getEpicById(6));
        System.out.println(taskManager.getSubById(7));
    }
}
