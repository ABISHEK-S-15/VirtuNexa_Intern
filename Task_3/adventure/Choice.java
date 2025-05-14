package adventure;

class Choice {
    public String text;
    public String nextNodeId;

    public Choice(String text, String nextNodeId) {
        this.text = text;
        this.nextNodeId = nextNodeId;
    }
}
