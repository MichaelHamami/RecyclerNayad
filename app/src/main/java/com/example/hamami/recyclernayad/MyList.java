package com.example.hamami.recyclernayad;

public class MyList {
    private String head;
    private String time;

    //constructor initializing values
    public MyList(String head, String time) {
        this.head = head;
        this.time = time;
    }

    //getters
    public String getHead() {
        return head;
    }

    public String getDesc() {
        return time;
    }
}

