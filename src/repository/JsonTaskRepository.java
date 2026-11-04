package repository;

import model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonTaskRepository implements TaskRepository{
    @Override
    public void save(List<Task> tasks) {
        try {
            Path filePath = Path.of("tasks.json");

            StringBuilder sb = new StringBuilder();

            sb.append("[");

            int counter = 0;

            for(Task task : tasks){
                counter++;

                sb.append("{");
                sb.append("\"id\": \"").append(task.getId()).append("\",");
                sb.append("\"description\": \"").append(task.getDescription()).append("\", ");
                sb.append("\"status\": \"").append(task.getStatus()).append("\", ");
                sb.append("\"createdAt\": \"").append(task.getCreatedAt()).append("\", ");
                sb.append("\"updatedAt\": \"").append(task.getUpdatedAt()).append("\"");
                if (tasks.size() == counter){
                    sb.append("},");
                }else{
                    sb.append("}");
                }
            }
            sb.append("]");
            Files.writeString(filePath, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> load() {
        ArrayList<Task> t = new ArrayList<>();
        return t;
    }
}
