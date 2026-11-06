package repository;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskRepository {
    void save(List<Task> tasks);
    ArrayList<Task> load();
}
