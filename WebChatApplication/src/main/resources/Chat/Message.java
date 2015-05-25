package MyPackage.WebChatApplication.src.main.resources.Chat;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Message implements JSONAware {
    private String author;
    private String text;

    public Message() {
        author = "user";
        text = "";
    }

    public Message(String text, String author) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Message parseMessage(JSONObject obj) {
        Message info = new Message();
        if ((String)obj.get("author") != null)
            info.name = (String)obj.get("author");
        info.text = (String)obj.get("text");
        return info;
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("author", author);
        obj.put("message", text);
        return obj.toString();
    }

    @Override
    public boolean equals(Object obj){
        return (((Message)obj).getAuthor() == author);
    }

    @Override
    public String toString() {
        return author + " : " + text;
    }
}