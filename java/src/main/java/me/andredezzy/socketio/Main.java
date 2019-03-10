package me.andredezzy.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;
import me.andredezzy.socketio.manager.EventCapturerManager;
import me.andredezzy.socketio.validator.EventObjectValidator;

import java.net.URISyntaxException;
import java.util.Arrays;

public class Main {

    private static EventCapturerManager eventCapturerManager;

    /**
     * Starting the capturer
     */
    public static void main(String[] args) throws URISyntaxException {
        final Socket socket = IO.socket("http://localhost:3000");

        eventCapturerManager = new EventCapturerManager();
        eventCapturerManager.add(new ExampleCapturer());

        socket.on("custom-event", Main::validateThenCapture);
        socket.connect();

        System.out.println("Event capturer has been started!\n");
    }

    /**
     * Validate the objects then capture them
     *
     * @param objects
     */
    private static void validateThenCapture(Object... objects) {
        Arrays.stream(objects).map(it -> new EventObjectValidator(it, eventCapturerManager))
            .filter(EventObjectValidator::hasTitleElement)
            .filter(EventObjectValidator::hasCapturer)
            .forEach(EventObjectValidator::capture);
    }
}
