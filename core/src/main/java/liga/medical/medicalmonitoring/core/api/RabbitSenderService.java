package liga.medical.medicalmonitoring.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.dto.RabbitMessageDTO;

public interface RabbitSenderService {
    void sendMessage(RabbitMessageDTO rabbitMessageDto, String queue) throws JsonProcessingException;
    void sendError(String error);
}
