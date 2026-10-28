import java.awt.*;
import java.time.Instant;

record Task(int id, String desription, String status, Instant createdAt, Instant updatedAt) {
    List tasks = new List(Task);


}

enum Status{
    TODO("todo"), IN_PROGRESS("in-progress"), DONE("done");

    private final String value;

    Status(String value){
        this.value = value;
    }

    String value(){
        return value;
    }
}
