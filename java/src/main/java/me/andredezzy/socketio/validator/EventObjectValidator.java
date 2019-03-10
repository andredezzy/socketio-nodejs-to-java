package me.andredezzy.socketio.validator;

import me.andredezzy.socketio.manager.EventCapturerManager;
import me.andredezzy.socketio.model.EventCapturer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class EventObjectValidator {

    private Object object;
    private EventCapturerManager eventCapturerManager;

    private String title;
    private JSONObject content;

    private EventCapturer capturer;

    public EventObjectValidator(Object object, EventCapturerManager eventCapturerManager) {
        this.object = object;
        this.eventCapturerManager = eventCapturerManager;

        this.build();
    }

    public boolean hasTitleElement() {
        return title != null;
    }

    public boolean hasCapturer() {
        return this.capturer != null;
    }

    public void capture(){
        try {
            this.capturer.call(this.content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void build() {
        try {
            JSONTokener tokener = new JSONTokener(object.toString());
            JSONObject jsonObject = new JSONObject(tokener);

            this.title = jsonObject.getString("title");
            this.content = jsonObject.getJSONObject("content");

            this.capturer = eventCapturerManager.getByKey(this.title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
