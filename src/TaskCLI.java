import model.Status;
import model.Task;
import service.TaskService;

import java.util.ArrayList;
import java.util.List;

class TaskCLI {
     static void main(String[] args) {
        String command;
        String firstTextField;
        String secondTextField;

        int askedId;

        TaskService ts = new TaskService();

        try {
            command = args[0];
            switch (command) {
                case "add" -> {
                    if(args.length == 2) {
                        firstTextField = args[1];
                        System.out.println(ts.add(firstTextField));
                    }else {
                        System.out.println("Usage: TaskCLI add [description]");
                    }
                }
                case "update" -> {
                    if (args.length == 3) {
                        try {
                            secondTextField = args[2];
                            askedId = Integer.parseInt(args[1]);
                            ts.update(askedId, secondTextField);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        System.out.println("Usage: TaskCLI update <id> [description]");
                    }

                }
                case "delete" -> {
                    if (args.length == 2) {
                        try {
                            askedId = Integer.parseInt(args[1]);
                            ts.delete(askedId);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        System.out.println("Usage: TaskCLI delete <id>");
                    }
                }
                case "mark-in-progress" -> {
                    if (args.length == 2) {
                        try {
                            askedId = Integer.parseInt(args[1]);
                            ts.markInProgress(askedId);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        System.out.println("Usage: TaskCLI mark-in-progress <id>");
                    }
                }
                case "mark-done" -> {
                    if (args.length == 2) {
                        try {
                            askedId = Integer.parseInt(args[1]);
                            ts.markDone(askedId);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        System.out.println("Usage: TaskCLI mark-done <id>");
                    }
                }
                case "list" -> {
                    List<Task> taskShow;
                    if (args.length == 2) {
                        try {
                            firstTextField = args[1];
                            Status status = switch (firstTextField) {
                                case "todo" -> Status.TODO;
                                case "done" -> Status.DONE;
                                case "in-progress" -> Status.IN_PROGRESS;
                                default -> throw new IllegalArgumentException("Unknown status");
                            };
                            taskShow = ts.list(status);

                            for (Task task : taskShow) {
                                System.out.println(task);
                            }
                        }catch (IllegalArgumentException e){
                            throw new IllegalArgumentException(e);
                        }
                    } else if(args.length == 1){
                        taskShow = ts.list();
                        for (Task task : taskShow) {
                            System.out.println(task);
                        }
                    }else{
                        System.out.println("Usage: TaskCLI list or TaskCLI list [parameter]");
                    }

                }
                default -> throw new IllegalStateException("Unexpected value: " + args[0]);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
