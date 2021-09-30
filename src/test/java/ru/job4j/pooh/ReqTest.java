package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;

public class ReqTest {
    @Test
    public void whenPostMethod() {
        String content = "POST /topic/weather HTTP/1.1\n"
                        + "Host: localhost:9000\n"
                        + "User-Agent: curl/7.67.0\n"
                        + "Accept: */*\n"
                        + "Content-Length: 7\n"
                        + "Content-Type: application/x-www-form-urlencoded\n\n"
                        + "text=13";
        Req req = Req.of(content);
        assertThat(req.method(), is("POST"));
        assertThat(req.mode(), is("topic"));
        assertThat(req.queue(), is("weather"));
        assertThat(req.param("text"), is("13"));
    }

    @Test
    public void whenGetMethodQueue() {
        String content = "GET /queue/weather HTTP/1.1\n"
                        + "Host: localhost:9000\n"
                        + "User-Agent: curl/7.67.0\n"
                        + "Accept: */*\n\n";
        Req req = Req.of(content);
        assertThat(req.method(), is("GET"));
        assertThat(req.mode(), is("queue"));
        assertThat(req.queue(), is("weather"));
    }

    @Test
    public void whenGetMethodTopic() {
        String content = "GET /topic/weather/1 HTTP/1.1\n"
                + "Host: localhost:9000\n"
                + "User-Agent: curl/7.67.0\n"
                + "Accept: */*\n\n";
        Req req = Req.of(content);
        assertThat(req.method(), is("GET"));
        assertThat(req.mode(), is("topic"));
        assertThat(req.queue(), is("weather"));
        assertThat(req.param("userId"), is("1"));
    }
}