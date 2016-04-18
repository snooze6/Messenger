package sample.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by denis on 18/04/16.
 */
public class Message {
    StringProperty content;
    StringProperty owner;

    // 0 is messages that you send
    // 1 is messages that you receive
    IntegerProperty type;


    public Message(StringProperty owner,StringProperty content, IntegerProperty type) {
        this.content = content;
        this.owner = owner;
        this.type = type;
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getOwner() {
        return owner.get();
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
    }

    public int getType() {
        return type.get();
    }

    public IntegerProperty typeProperty() {
        return type;
    }

    public void setType(int type) {
        this.type.set(type);
    }
}
