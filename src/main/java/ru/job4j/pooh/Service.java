package ru.job4j.pooh;

import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

public interface Service {
    Resp process(Req req);
}
