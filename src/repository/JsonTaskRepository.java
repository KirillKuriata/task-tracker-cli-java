package repository;

import model.Status;
import model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JsonTaskRepository implements TaskRepository{
    private final Path FILE_PATH = Path.of("tasks.json");

    @Override
    public void save(List<Task> tasks) {
        try {
            StringBuilder sb = new StringBuilder();

            if(tasks.size() > 1) {
                sb.append("[");
            }

            int counter = 0;

            for(Task task : tasks){
                counter++;

                sb.append("{");
                sb.append("\"id\": \"").append(task.getId()).append("\",");
                sb.append("\"description\": \"").append(task.getDescription()).append("\", ");
                sb.append("\"status\": \"").append(task.getStatus()).append("\", ");
                sb.append("\"createdAt\": \"").append(task.getCreatedAt()).append("\", ");
                sb.append("\"updatedAt\": \"").append(task.getUpdatedAt()).append("\"");
                if (tasks.size() > counter){
                    if (tasks.size() > 1) {
                        sb.append("},");
                    }
                }else{
                    sb.append("}");
                }
            }
            if(tasks.size() > 1) {
                sb.append("]");
            }
            Files.writeString(FILE_PATH, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Task> load() {
        ArrayList<Task> storedTasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)){
            return new ArrayList<>();
        }

        try {
            String jsonContent = Files.readString(FILE_PATH);
            String[] tasks = jsonContent
                    .replace("[", "")
                    .replace("]", "")
                    .split("},");

            for(String task : tasks){
                task = task
                        .replace("{", "")
                        .replace("}", "")
                        .replace("\"", "")
                        .strip();
                String[] fields = task.split(",");

                int id = Integer.parseInt(
                        fields[0].substring( fields[0].indexOf(":")+1).strip()
                );
                String description = fields[1].substring( fields[1].indexOf(":")+1).strip();
                Status status = Status.valueOf(
                        fields[2].substring( fields[2].indexOf(":")+1).strip()
                );
                Instant createdAt = Instant.parse(
                        fields[3].substring( fields[3].indexOf(":")+1).strip()
                );
                Instant updatedAt = Instant.parse(
                        fields[4].substring( fields[4].indexOf(":")+1).strip()
                );

                Task taskForList = new Task(id, description, status, createdAt, updatedAt);
                storedTasks.add(taskForList);
            }
        return storedTasks;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
