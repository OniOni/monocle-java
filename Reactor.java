import java.util.LinkedList;
import java.util.List;

class Reactor {
    interface Task {
        int run(Object... args);
    }

    private LinkedList<Task> todo;

    public Reactor() {
        todo = new LinkedList<Task>();
    }

    public void appendTask(Task task) {
        todo.addFirst(task);
    }

    public int runNext() {
        Task next = todo.removeLast();

        return next.run(0);
    }

    public static void main(String... args) {
        Reactor r = new Reactor();

        Task t = (a) -> {
            System.out.println("Yo");
            return 0;
        };

        r.appendTask(t);
        r.runNext();
    }
}
