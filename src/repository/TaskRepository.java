package repository;

import model.Task;

import java.util.List;

public interface TaskRepository {
    public void save(List<Task> tasks);
    public List<Task> load();
}
