package service;

import model.Status;
import model.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskService {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public void add(String description){
        tasks.stream()
                .max(Comparator.comparingInt(Task::getId))
                .ifPresentOrElse(lastTask -> tasks.add(new Task(lastTask.getId() + 1, description, Status.TODO, Instant.now(), Instant.now())),
                        () -> tasks.add(new Task(1, description, Status.TODO, Instant.now(), Instant.now())));
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
