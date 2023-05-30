package Services;

import Module.Epic;
import Module.Status;
import Module.Task;
import Module.SubTask;


import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

    private HashMap<Integer, Task> allTasks = new HashMap<>();
    private HashMap<Integer, Epic> allEpics = new HashMap<>();
    private HashMap<Integer, SubTask> allSubTasks = new HashMap<>();

    private int countOfTasks = 0;
    private int countOfEpics = 0;
    private int countOfSubTasks = 0;

    public Manager() {
    }

    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        for (int key : allTasks.keySet()) {
            tasks.add(allTasks.get(key));
        }
        return tasks;
    }

    public Task getTaskById(int id){
        return allTasks.get(id);
    }

    public void deleteTaskById(int id){
        allTasks.remove(id);
    }

    public void deleteAllTasks(){
        allTasks.clear();
    }

    public void addTask(Task task){
        task.setStatus(Status.NEW);
        task.setId(++countOfTasks);
        allTasks.put(countOfTasks, task);
    }

    public void saveTask(int id, Task task){
        for(int key : allTasks.keySet()){
            if (id == allTasks.get(key).getId()){
                task.setId(id);
                allTasks.put(id, task);
            }
        }
    }
    public ArrayList<SubTask> getSubTasksByEpic(int epicId) {
        ArrayList<SubTask> epicsSubTasks = new ArrayList<>();
        for (int key : allSubTasks.keySet()){
            if (allSubTasks.get(key).getEpicId() == epicId){
                epicsSubTasks.add(allSubTasks.get(key));
            }
        }
        return epicsSubTasks;
    }
    public ArrayList<SubTask> getAllSubTasks(){
        ArrayList<SubTask> subTasks = new ArrayList<>();
        for (int key : allSubTasks.keySet()){
            subTasks.add(allSubTasks.get(key));
        }
        return subTasks;
    }

    public void addEpic(Epic epic) {
        epic.setId(++countOfEpics);
        epic.setStatus(epicStatus(epic));
        allEpics.put(countOfEpics, epic);
    }

    public void deleteAllEpics() {
        allEpics.clear();
    }

    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        for (int key : allEpics.keySet()) {
            epics.add(allEpics.get(key));
        }
        return epics;
    }
    public void addSubtask(SubTask subTask){
        subTask.setId(++countOfSubTasks);
        subTask.setStatus(Status.NEW);
        allSubTasks.put(countOfSubTasks, subTask);
        allEpics.get(subTask.getEpicId()).getSubTaskIds().add(countOfSubTasks);
    }
    public void saveSubTask(int id,  SubTask subTask){
        subTask.setId(id);
        for (int key : allSubTasks.keySet()){
            if (key == id){
                allSubTasks.put(key, subTask);
                allEpics.get(subTask.getEpicId()).setStatus(epicStatus(allEpics.get(subTask.getEpicId())));
            }
        }
    }
    public void saveEpic(int id, Epic epic) {
        for (int key : allEpics.keySet()){
            if (id == allEpics.get(key).getId()){
                epic.setId(id);
                epic.setStatus(epicStatus(epic));
                epic.setSubTaskIds(allEpics.get(key).getSubTaskIds());
                allEpics.put(id, epic);
            }
        }

    }
    public Status epicStatus(Epic epic){
        Status status;
        int epicIsNew = 0; int epicIsDone = 0;
        for (int key : allSubTasks.keySet()){
            if (allSubTasks.get(key).getEpicId() == epic.getId()){
                if (allSubTasks.get(key).getStatus() == Status.NEW) epicIsNew++;
                else if (allSubTasks.get(key).getStatus() == Status.DONE) epicIsDone++;
            }
        }
        if (epicIsNew == epic.getSubTaskIds().size() || epic.getSubTaskIds().size() == 0) status = Status.NEW;
        else if (epicIsDone == epic.getSubTaskIds().size()) status = Status.DONE;
        else status = Status.IN_PROGRESS;
        return status;
    }
    public void deleteEpicById(int id){
        for (int key : allSubTasks.keySet()){
            if (allSubTasks.get(key).getEpicId() == id){
                allSubTasks.remove(key);
            }
        }
        allEpics.remove(id);
    }
    public void deleteSubTaskById(int id){
        Epic epic;
        epic = allEpics.get(allSubTasks.get(id).getEpicId());
        epic.getSubTaskIds().remove(id);
        allEpics.get(epic.getId()).setSubTaskIds(epic.getSubTaskIds());
        allSubTasks.remove(id);
        allEpics.get(epic.getId()).setStatus(epicStatus(epic));
     }
    public void deleteAllSubTasks(){
        allSubTasks.clear();
        Epic epic;
        ArrayList<Integer> subIds = new ArrayList<>();
        for (int key : allEpics.keySet()){
            epic = allEpics.get(key);
            subIds = allEpics.get(key).getSubTaskIds();
            subIds.clear();
            allEpics.get(key).setSubTaskIds(subIds);
            allEpics.get(key).setStatus(epicStatus(epic));
        }
    }
    public Epic getEpicById (int id) {
        return allEpics.get(id);
    }
}