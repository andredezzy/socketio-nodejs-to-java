const http = require("http");
const express = require("express");
const bodyParser = require("body-parser");

const app = express();

const server = http.Server(app);
const io = require("socket.io")(server);

app.use((req, res, next) => {
    req.io = io;

    return next();
});

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

const sendEventController = require("./controllers/sendEventController");

app.post("/send", sendEventController.sendEvent);

io.on("connection", socket => {
    console.log("a capturer was connected");

    socket.on("disconnect", () => {
        console.log("a capturer was disconnected");
    });
});

server.listen(process.env.PORT || 3000, () => {
    console.log(
        `Server started at: http://localhost:${process.env.PORT || 3000}`
    );
});
