import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Message implements JSONAware {
    static int currId = 1;
    private String userName;
    private int id;
    private String text;
    private boolean deleted = false;
    private boolean changed = false;

    public Message() {
        userName = "1";
        text = "";
        id = currId++;
    }

    public Message(String text, String userName) {
        this.userName = userName;
        this.text = text;
        id = currId++;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void deleteMessage() {
        if (!deleted) {
            this.text = "message is deleted.";
            this.setDeleted(true);
        }
    }

    public static Message parseMessage(JSONObject obj) {
        Message info = new Message();
        if ((String)obj.get("user") != null)
            info.userName = (String)obj.get("user");
        info.text = (String)obj.get("message");
        info.id = Integer.parseInt(obj.get("id").toString());
        return info;
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("user", userName);
        obj.put("message", text);
        obj.put("id", id);
        return obj.toString();
    }

    @Override
    public String toString() {
        return userName + ": " + text;
    }

    @Override
    public boolean equals(Object obj) {
        return (((Message)obj).getID() == id);
    }
}