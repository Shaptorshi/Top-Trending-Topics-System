package src1.model;


public class Event{
    String topic;
    int timeStamp;

    public Event(String topic,int timeStamp){
        this.topic = topic;
        this.timeStamp = timeStamp;
    }

    public String getTopic(){
        return topic;
    }

    public int getTimeStamp(){
        return timeStamp;
    }
}