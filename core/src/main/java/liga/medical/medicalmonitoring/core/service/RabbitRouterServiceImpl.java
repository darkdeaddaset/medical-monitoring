package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.dto.RabbitMessageDto;
import liga.medical.medicalmonitoring.core.api.RabbitRouterService;
import liga.medical.medicalmonitoring.core.config.ExchangeConfig;
import liga.medical.medicalmonitoring.core.model.NameQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitRouterServiceImpl implements RabbitRouterService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void routeMessage(String message) {
        rabbitTemplate.setExchange(ExchangeConfig.DIRECT_EXCHANGE_NAME);

        try {
            RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
            rabbitTemplate.convertAndSend(rabbitMessageDto.getMessageType().toString(), message);
            System.out.println("Роутер перенаправил сообщение при помощи кастомного обменика");
        } catch (JsonProcessingException e) {
            rabbitTemplate.convertAndSend(NameQueue.ERROR.getStr(), e.getMessage());
            System.out.println("При перенаправлении сообщения произошла ошибка");
        }
    }

    /*@Override
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
    }*/


}
