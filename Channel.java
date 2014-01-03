import java.util.LinkedList;

class Channel {
    private LinkedList<String> in;

    public Channel() {
        in = new LinkedList<>();
    }

    public void write(String s) {
        in.addFirst(s);
    }


    public String readNonBlocking() {
        return in.pollLast();
    }

    public synchronized String read() {
        String res;
        while ((res = in.pollLast()) == null);

        return res;
    }
}
