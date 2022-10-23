package liga.medical.medicalmonitoring.core.api;

public interface RabbitSenderError extends RabbitSenderService {
    void sendError(String error);
}
