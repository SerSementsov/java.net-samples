package chat.common;

public class MessageImpl implements Message {

    private String sender;
    private String content;
    private int type;

    public MessageImpl(String sender, String content, int type) {
        this.sender = sender;
        this.content = content;
        this.type = type;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public int getType() {
        return type;
    }
}
