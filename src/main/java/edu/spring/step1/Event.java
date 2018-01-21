package edu.spring.step1;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Event {

    private int id;
    private String msg;
    private Date date;

    public Event(Date date) {
        this.date = date;
        this.id = ThreadLocalRandom.current().nextInt();
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
