package me.andredezzy.socketio;

import me.andredezzy.socketio.model.EventCapturer;
import org.json.JSONException;
import org.json.JSONObject;

public class ExampleCapturer extends EventCapturer {

    public ExampleCapturer() {
        super("example_capturer");
    }

    @Override
    public void call(JSONObject object) throws JSONException {
        System.out.println(object.toString(4));
    }
}
