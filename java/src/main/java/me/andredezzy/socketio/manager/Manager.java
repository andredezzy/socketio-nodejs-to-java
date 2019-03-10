package me.andredezzy.socketio.manager;

import java.util.LinkedList;
import java.util.List;

public abstract class Manager<T> {

    protected List<T> elements = new LinkedList<>();

    abstract public <K> T getByKey(K k);

    public void add(T t) {
        elements.add(t);
    }

    public void remove(T t) {
        elements.remove(t);
    }
}
