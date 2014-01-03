class MyFuture {

    private Reactor r;
    private Reactor.Task operation;
    private Channel chan;

    public MyFuture(Reactor r, Reactor.Task op) {
        this.r = r;
        this.operation = op;
        this.chan = new Channel();

        this.r.appendTask((Reactor.Task)op, chan);
    }

    public Object get() {
        String res;

        while ((res = this.chan.readNonBlocking()) == null) {
            this.r.runNext();
        }

        return res;
    }

}
