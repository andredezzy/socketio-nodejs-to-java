package me.andredezzy.socketio.manager;

import me.andredezzy.socketio.model.EventCapturer;

public class EventCapturerManager extends Manager<EventCapturer> {

    @Override
    public <K> EventCapturer getByKey(K k) {
        return this.elements.stream()
            .filter(it -> it.getTitle().equals(k))
            .findFirst()
            .orElse(null);
    }
}
