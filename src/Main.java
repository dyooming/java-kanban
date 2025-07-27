
public class Main {

    public static void main(String[] args) {

//        System.out.println("Поехали!");
//        Managers.Managers.TaskManager taskManager = Managers.Managers.getDefault();
//        Managers.Managers.HistoryManager historyManager = Managers.Managers.getDefaultHistory();
//
//
//        System.out.println("-----Создать задачу-----");
//
//        // Создаем две таски
//        Task.Task task1 = new Task.Task("Сделать тренировочный план на неделю", "Дедлайн - пятница", Task.TaskStatus.NEW);
//        taskManager.createTask(task1);
//        System.out.println(taskManager.getTaskById(1));
//        Task.Task task2 = new Task.Task("Снять инструкцию по технике выполнения упражнений", "С двух камер", Task.TaskStatus.IN_PROGRESS);
//        taskManager.createTask(task2);
//        System.out.println(taskManager.getTaskById(2));
//
//        // Создаем эпик с 2 подзадачами
//        Task.Epic epic1 = new Task.Epic("День ног", "Свободные веса");
//        taskManager.createEpic(epic1);
//        Task.Sub sub1 = new Task.Sub("Присед", "4 подхода по 10 повторений", Task.TaskStatus.NEW, epic1.getId());
//        taskManager.createSub(sub1);
//        Task.Sub sub2 = new Task.Sub("Выпады", "3 подхода по 12 повторений", Task.TaskStatus.NEW, epic1.getId());
//        taskManager.createSub(sub2);
//
//        System.out.println(taskManager.getEpicById(3));
//        System.out.println(taskManager.getSubById(4));
//        System.out.println(taskManager.getSubById(5));
//
//
//        // Создаем эпик с 1 подзадачей
//        Task.Epic epic2 = new Task.Epic("Заминка", "Легкий темп");
//        taskManager.createEpic(epic2);
//        Task.Sub sub3 = new Task.Sub("Растяжка", "ТБС, задняя поверхность бедра", Task.TaskStatus.NEW, epic2.getId());
//        taskManager.createSub(sub3);
//
//        System.out.println(taskManager.getEpicById(6));
//        System.out.println(taskManager.getSubById(7));
//
//        System.out.println("\n-----Обновить статус задач-----");
//        Task.Task updtTask1 = new Task.Task(task1.getId(), "Переделать тренировочный план", "Добавить упражнения на спину", Task.TaskStatus.IN_PROGRESS);
//        taskManager.updateTask(updtTask1);
//        Task.Task updtTask2 = new Task.Task(task2.getId(), "Налоги", "Разобраться с налоговой отчетностью", Task.TaskStatus.DONE);
//        taskManager.updateTask(updtTask2);
//
//        Task.Sub updtSub1 = new Task.Sub(sub1.getId(), "Разгибание голени", "5 подходов по 15 повторений", Task.TaskStatus.DONE, sub1.getEpicId());
//        taskManager.updateSub(updtSub1);
//        Task.Sub updtSub2 = new Task.Sub(sub2.getId(), "Платформа", "4 подхода по 12 повторений", Task.TaskStatus.DONE, sub2.getEpicId());
//        taskManager.updateSub(updtSub2);
//        Task.Sub updtSub3 = new Task.Sub(sub3.getId(), "Кардио тренировка", "Эллипс - 60 минут", Task.TaskStatus.IN_PROGRESS, sub3.getEpicId());
//        taskManager.updateSub(updtSub3);
//
//        System.out.println(taskManager.getTaskById(1));
//        System.out.println(taskManager.getTaskById(2));
//        System.out.println(taskManager.getEpicById(3));
//        System.out.println(taskManager.getSubById(4));
//        System.out.println(taskManager.getSubById(5));
//        System.out.println(taskManager.getEpicById(6));
//        System.out.println(taskManager.getSubById(7));
//
//        System.out.println("\n-----Удалить две задачи и вывести все задачи-----");
//        taskManager.deleteTask(2);
//        taskManager.deleteEpic(6);
//
//
//        System.out.println(taskManager.getTaskById(1));
//        System.out.println(taskManager.getTaskById(2));
//        System.out.println(taskManager.getEpicById(3));
//        System.out.println(taskManager.getSubById(4));
//        System.out.println(taskManager.getSubById(5));
//        System.out.println(taskManager.getEpicById(6));
//        System.out.println(taskManager.getSubById(7));

//
//        historyManager.printHistory();
    }
}