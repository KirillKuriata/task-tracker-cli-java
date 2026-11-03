import model.Status;
import model.Task;
import service.TaskService;

import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        String command = args[0];
        String text = args[1];

        TaskService ts = new TaskService();

        switch (command) {
            case "add" -> {}
            case "update" -> {}
            case "delete" -> {}
            case "mark-in-progress" -> {}
            case "mark-done" -> {}
            case "list" -> {
                ArrayList<Task> taskShow = new ArrayList<>();
                if (args[1] != null) {
                    Status status = switch (text) {
                        case "todo" -> Status.TODO;
                        case "done" -> Status.DONE;
                        case "in-progress" -> Status.IN_PROGRESS;
                        default -> throw new IllegalArgumentException("Unknown status");
                    };
                    taskShow = ts.list(status);
                }else{
                    taskShow = ts.list();
                }
                for(Task task : taskShow){

                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + args[0]);
        }
    }
}
