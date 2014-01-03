import java.util.LinkedList;

class Reactor {
    interface Task {
        int run(Object... args);
    }

    private class ReactorPair {
        public Task task;
        public Channel chan;

        ReactorPair(Task t, Channel c) {
            this.task = t;
            this.chan = c;
        }
    }

    private LinkedList<ReactorPair> todo;

    public Reactor() {
        todo = new LinkedList<ReactorPair>();
    }

    public void appendTask(Task task) {
        todo.addFirst(new ReactorPair(task, null));
    }

    public void appendTask(Task task, Channel chan) {
        todo.addFirst(new ReactorPair(task, chan));
    }

    public int runNext() {
        ReactorPair next = todo.removeLast();

        int res = next.task.run(0);

        if (next.chan != null) {
            next.chan.write(new Integer(res).toString());
        }

        return 0;
    }

    public void run() {
        while (todo.size() != 0) {
            this.runNext();
        }
    }

    public static void main(String... args) {
        Reactor r = new Reactor();

        Task t = (a) -> {
            System.out.println("Yo");
            return 0;
        };

        r.appendTask(t);
        r.run();
    }
}
