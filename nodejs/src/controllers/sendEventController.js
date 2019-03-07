module.exports = {
    async sendEvent(req, res, next) {
        try {
            req.io.emit("custom-event", req.body);

            return res.status(200).json(req.body);
        } catch {
            const error = {
                status: 404,
                message: 'Unable to send event.'
            };

            return next(error);
        }
    }
};
