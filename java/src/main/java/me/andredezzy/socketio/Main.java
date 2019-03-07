package me.andredezzy.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;
import me.andredezzy.socketio.manager.EventCapturerManager;
import me.andredezzy.socketio.model.EventCapturer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.URISyntaxException;

public class Main {

    private static EventCapturerManager eventCapturerManager;

    public static void main(String[] args) throws URISyntaxException {
        final Socket socket = IO.socket("http://localhost:3000");

        eventCapturerManager = new EventCapturerManager();
        eventCapturerManager.add(new ExampleCapturer());

        socket.on("custom-event", Main::capture);
        socket.connect();

        System.out.println("Event capturer has been started!\n");
    }

    private static void capture(Object... objects){
        try {
            JSONTokener tokener = new JSONTokener(objects[0].toString());
            JSONObject object = new JSONObject(tokener);

            String title = object.getString("title");

            EventCapturer eventCapturerByKey = eventCapturerManager.getByKey(title);

            if (eventCapturerByKey != null) {
                JSONObject contentObject = object.getJSONObject("content");

                eventCapturerByKey.call(contentObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
