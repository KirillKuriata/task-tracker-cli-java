package service;

import model.Status;
import model.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskService {
    ArrayList<Task> tasks = new ArrayList<>();

    public String add(String description){
        int newId = tasks.stream()
                .max(Comparator.comparing(Task::getId))
                .map(task -> task.getId() + 1)
                .orElse(1);

        tasks.addLast(new Task(newId, description, Status.TODO, Instant.now(), Instant.now()));
        return "Task added successfully (ID: " + newId + " )";
    }

    public void update(int targetId, String newDescription){
        tasks.stream()
                .filter(findTask -> findTask.getId() == targetId)
                .findAny()
                .ifPresent(task -> {
                    task.setDescription(newDescription);
                    task.setUpdatedAt(Instant.now());
                });
    }
    public void delete(int targetId){
        tasks.stream()
                .filter(findTask -> findTask.getId() == targetId)
                .findAny()
                .ifPresent(tasks::remove);
    }
    public void markInProgress(int targetId){
        tasks.stream()
                .filter(findTask -> findTask.getId() == targetId)
                .findAny()
                .ifPresent(task -> {
                    task.setStatus(Status.IN_PROGRESS);
                    task.setUpdatedAt(Instant.now());
                });
    }
    public void markDone(int targetId){
        tasks.stream()
                .filter(findTask -> findTask.getId() == targetId)
                .findAny()
                .ifPresent(task -> {
                    task.setStatus(Status.DONE);
                    task.setUpdatedAt(Instant.now());
                });
    }
    public ArrayList<Task> list(){
        return tasks;
    }
    public ArrayList<Task> list(Status status){
        ArrayList<Task> sortedByStatusTasks = new ArrayList<>();
        tasks.stream()
                .filter(findTask -> findTask.getStatus() == status)
                .findAny()
                .ifPresent(sortedByStatusTasks::addLast);
        return sortedByStatusTasks;
    }
}
