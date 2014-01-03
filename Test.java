class Test {

    public static void pingpong(Reactor r) {
        Channel c = new Channel();

        System.out.println("Ping");
        new MyFuture(r, (a) -> {
                try {
                    System.out.println("Going to sleep.");
                    Thread.sleep(3000);
                    System.out.println("UP!!");
                    c.write("Channel test");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return 0;
        });

        System.out.println("Pong");
        new MyFuture(r, (a) -> {
                String s = c.read();
                System.out.println(s);

                return 0;
        });
    }

    public static void test(Reactor r) {
        Object val = new MyFuture(r, (a) -> {
                try {
                    System.out.println("Yo");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return 42;
        }).get();

        System.out.println(val);
    }

    public static void main(String... args)
    throws InterruptedException {
        Reactor r = new Reactor();

        r.appendTask((a) -> {
                test(r);
                pingpong(r);
                return 0;
            });

        System.out.println("Yeah");

        r.run();
    }
}
