package model;

public enum Status{
    TODO("todo"), IN_PROGRESS("in-progress"), DONE("done");

    private final String value;

    Status(String value){
        this.value = value;
    }

    String value(){
        return value;
    }
}