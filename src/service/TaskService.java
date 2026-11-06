package service;

import model.Status;
import model.Task;
import repository.JsonTaskRepository;
import repository.TaskRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskService {
    List<Task> tasks = new ArrayList<>();

    TaskRepository tr = new JsonTaskRepository();

    public String add(String description){
        tasks = tr.load();

        int newId = tasks.stream()
                .max(Comparator.comparing(Task::getId))
                .map(task -> task.getId() + 1)
                .orElse(1);

        tasks.addLast(new Task(newId, description, Status.TODO, Instant.now(), Instant.now()));
        tr.save(tasks);
        return "Task added successfully (ID: " + newId + " )";
    }

    public void update(int targetId, String newDescription){
        tasks = tr.load();

        tasks.stream()
                .filter(findTask -> findTask.getId() == targetId)
                .findAny()
                .ifPresent(task -> {
                    task.setDescription(newDescription);
                    task.setUpdatedAt(Instant.now());
                });

        tr.save(tasks);
    }
    public void delete(int targetId){
        tasks = tr.load();

        tasks.stream()
                .filter(findTask -> findTask.getId() == targetId)
                .findAny()
                .ifPresent(tasks::remove);

        tr.save(tasks);
    }
    public void markInProgress(int targetId){
        tasks = tr.load();

        tasks.stream()
                .filter(findTask -> findTask.getId() == targetId)
                .findAny()
                .ifPresent(task -> {
                    task.setStatus(Status.IN_PROGRESS);
                    task.setUpdatedAt(Instant.now());
                });

        tr.save(tasks);
    }
    public void markDone(int targetId){
        tasks = tr.load();

        tasks.stream()
                .filter(findTask -> findTask.getId() == targetId)
                .findAny()
                .ifPresent(task -> {
                    task.setStatus(Status.DONE);
                    task.setUpdatedAt(Instant.now());
                });

        tr.save(tasks);
    }
    public List<Task> list(){
        tasks = tr.load();
        return tasks;
    }
    public List<Task> list(Status status){
        tasks = tr.load();

        return tasks.stream()
                .filter(Task -> Task.getStatus() == status)
                .toList();
    }
}
