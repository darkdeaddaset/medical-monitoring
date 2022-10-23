package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.dto.RabbitMessageDto;
import liga.medical.dto.enums.MessageType;
import liga.medical.medicalmonitoring.core.api.RabbitRouterService;
import liga.medical.medicalmonitoring.core.api.RabbitSenderError;
import liga.medical.medicalmonitoring.core.model.NameQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitRouterServiceImpl implements RabbitRouterService {
    private final ObjectMapper objectMapper;
    private final RabbitSenderError rabbitSenderError;

    @Override
    public void routeMessage(String message) {
        try {
            RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
            MessageType messageType = rabbitMessageDto.getMessageType();

            switch (messageType) {
                case DAILY:
                    rabbitSenderError.sendMessage(rabbitMessageDto, NameQueue.DAILY.getStr());
                    break;
                case ALERT:
                    rabbitSenderError.sendMessage(rabbitMessageDto, NameQueue.ALERT.getStr());
                    break;
                case ERROR:
                    rabbitSenderError.sendMessage(rabbitMessageDto, NameQueue.ERROR.getStr());
                    break;
                default:
                    rabbitSenderError.sendError("ERROR");
                    break;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
