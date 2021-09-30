package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {

    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> map = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "";
        int status = 200;

        if ("GET".equals(req.method())) {
            if (map.isEmpty()) {
                text = "There are no queues at all";
                status = 404;
            } else if (map.get(req.queue()) == null) {
                status = 404;
                text = "There are no queue with name " + req.queue();
            } else if (map.get(req.queue()).isEmpty()) {
                text = "Queue: " + req.queue() + " is empty";
            } else {
                text = map.get(req.queue()).poll();
            }
        } else if ("POST".equals(req.method())) {
            map.putIfAbsent(req.queue(), new ConcurrentLinkedQueue<>());
            for (String key: req.getParams().keySet()) {
                text = req.param(key);
            }
            map.get(req.queue()).add(text);
        }

        return new Resp(text, status);
    }
}
