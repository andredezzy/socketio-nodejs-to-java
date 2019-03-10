package me.andredezzy.socketio;

import me.andredezzy.socketio.model.EventCapturer;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Example Capturer
 *
 * Endpoint: https://github.com/andredezzy/socketio-nodejs-to-java/blob/master/nodejs/.env (EVENT_EMITTER_ENDPOINT)
 * You'll send:
 * {
 *     "title": "example_capturer",
 *     "content": {
 *         "message": "it's a test example capturer"
 *     }
 * }
 *
 * @method call will receive the "content" object
 */
public class ExampleCapturer extends EventCapturer {


    /**
     * Instantiates the capturer.
     *
     * @param title the JSON object string element "title" of the HTTP request body
     */
    public ExampleCapturer() {
        super("example_capturer");
    }

    /**
     * Receiver method
     *
     * @param object the JSON object element "content" of the HTTP request body
     * @throws JSONException
     */
    @Override
    public void call(JSONObject object) throws JSONException {
        System.out.println(object.toString(4));
    }
}
