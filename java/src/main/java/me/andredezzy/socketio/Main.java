package me.andredezzy.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        final Socket socket = IO.socket("http://localhost:3000");

        socket.on("sent_event", new Emitter.Listener() {
            public void call(Object... args) {
                try {
                    JSONTokener tokener = new JSONTokener(args[0].toString());
                    JSONObject object = new JSONObject(tokener);

                    String title = object.getString("title");

                    if (title.equalsIgnoreCase("tablist_update")) {
                        JSONObject content = object.getJSONObject("content");
                        String header = content.getString("header");
                        String footer = content.getString("footer");

                        System.out.println("header => " + header);
                        System.out.println("footer => " + footer);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        socket.connect();
    }
}
