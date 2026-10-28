class Main {
    public static void main(String[] args) {
        String command = args[0];
        String text = args[1];

        Task task;

        switch (command) {
            case "add" -> {}
            case "update" -> {}
            case "delete" -> {}
            case "mark-in-progress" -> {}
            case "mark-done" -> {}
            case "list" -> {
                if(text != null) {
                    task.list();
                }else{
                    task.list(status);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + args[0]);
        }

        Status status = switch (text) {
            case "todo" -> Status.TODO;
            case "done" -> Status.DONE;
            case "in-progress" -> Status.IN_PROGRESS;
            default -> throw new IllegalArgumentException("Unknown status");
        };
    }
}
