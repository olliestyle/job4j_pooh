package ru.job4j.pooh;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueueServiceTest {
    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        Map<String, String> params = new HashMap<>();
        params.put("temperature", "18");
        queueService.process(
                new Req("POST", "queue", "weather", params)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text(), is("18"));
    }

    @Test
    public void whenNoQueuesAtAll() {
        QueueService queueService = new QueueService();
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text(), is("There are no queues at all"));
    }

    @Test
    public void whenQueueIsEmpty() {
        QueueService queueService = new QueueService();
        Map<String, String> params = new HashMap<>();
        params.put("temperature", "18");
        queueService.process(
                new Req("POST", "queue", "weather", params)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text(), is("18"));
        Resp nextResult = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(nextResult.text(), is("Queue: weather is empty"));
    }

    @Test
    public void whenNoQueueWithSuchName() {
        QueueService queueService = new QueueService();
        Map<String, String> params = new HashMap<>();
        params.put("temperature", "18");
        queueService.process(
                new Req("POST", "queue", "weather", params)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "sport", null)
        );
        assertThat(result.text(), is("There are no queue with name sport"));
    }
}