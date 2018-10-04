package io.hasenpower.hpchat.model;

public class Message {

    private long timestamp;
    private String text;
    private User sender;

    public Message() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", text='" + text + '\'' +
                ", sender=" + sender +
                '}';
    }
}
