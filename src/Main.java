import Module.Epic;
import Module.Status;
import Module.SubTask;
import Module.Task;
import Services.Manager;

public class Main {
    public static void main(String[] args) {

        //toString переопределялся, чтобы выводить в начале тип задачи, а не Task повсюду
        //У меня ведь есть метод addSubTask, я не понимаю в чем он некорректен?
        //С заменой Хэша пришлось повозиться, ведь было удобно, что при удалении Эпика сразу удалялись его Сабы

        Manager manager = new Manager();
        // Инициализируем задачи типа Task
        Task task1 = new Task( "Task1", "Description1");
        Task task2 = new Task( "Task2", "Description2");
        Task changedTask = new Task("changedTask", "changedDescription", Status.IN_PROGRESS);
        // Инициализируем задачи типа Epic
        Epic epic1 = new Epic("epic1","epicDescription1");
        Epic epic2 = new Epic("epic2","epicDescription2");
        Epic changedEpic = new Epic( "changedEpic","changedDescription");
        // Инициализируем задачи типа SubTask
        SubTask subTask1 = new SubTask( "sub1-1", "des1-1", 1);
        SubTask subTask2 = new SubTask( "sub1-2", "des1-2", 1);
        SubTask changedSubTask = new SubTask( "sub2-1", "des2-1", 2);
        SubTask subTask4 = new SubTask("sub1-1-1", "des1-1-1", 1, Status.IN_PROGRESS);
        // Создаем задачи типа Task
        manager.addTask(task1);
        manager.addTask(task2);
        // Создаем задачи типа Epic
        manager.addEpic(epic1);
        manager.addEpic(epic2);
        // Создаем задачи типа SubTask
        manager.addSubtask(subTask1);
        manager.addSubtask(subTask2);
        manager.addSubtask(changedSubTask);
        System.out.println(manager.getAllSubTasks().size());
        System.out.println("Вывод всех задач");
        System.out.println(manager.getAllTasks());
        System.out.println("Вывод всех Epic");
        System.out.println(manager.getAllEpics());
        System.out.println("Вывод всех SubTask");
        System.out.println(manager.getAllSubTasks());
        System.out.println("Вывод обновленных задач");
        //Обновление Task
        manager.saveTask(2, changedTask);
        //Обновление Epic
        manager.saveEpic(2, changedEpic);
        //Обновление подзадачи
        manager.saveSubTask(2, subTask4);
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubTasks());
        // Удаление задачи по номеру
        manager.deleteTaskById(2);
        // Удаление Epic по номеру
        manager.deleteEpicById(2);
        // Удаление SubTask по номеру
        manager.deleteSubTaskById(1);
        System.out.println("Вывод задач после удаления");
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubTasks());
        //manager.deleteAllTasks();
        //manager.deleteAllEpics();
        manager.deleteAllSubTasks();
        System.out.println(manager.getAllEpics());
    }
}