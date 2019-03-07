package me.andredezzy.socketio.model;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class EventCapturer {

    private String title;

    public EventCapturer(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    abstract public void call(JSONObject object) throws JSONException;
}
