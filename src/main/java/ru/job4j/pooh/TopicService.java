package ru.job4j.pooh;

public class TopicService implements Service {
    @Override
    public Resp process(Req req) {
        String text = "";
        int status = 200;

        if ("GET".equals(req.method())) {
            System.out.println("get");
        }
        if ("POST".equals(req.method())) {
            System.out.println("post");
        }

        return new Resp(text, status);
    }
}
