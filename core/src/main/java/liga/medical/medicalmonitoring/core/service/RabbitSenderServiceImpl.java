package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.dto.RabbitMessageDto;
import liga.medical.medicalmonitoring.core.api.RabbitSenderService;
import liga.medical.medicalmonitoring.core.model.NameQueue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RabbitSenderServiceImpl implements RabbitSenderService {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(RabbitMessageDto rabbitMessageDto, String queue) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(rabbitMessageDto);
        amqpTemplate.convertAndSend(queue, message);
        System.out.printf("Сообщение [%s] в очередь [%s] отправлено%n", message, queue);
    }

    @Override
    public void sendError(String error) {
        amqpTemplate.convertAndSend(NameQueue.ERROR.getStr(), error);
        System.out.println("В сообщении ошибка. В очередь ERROR");
    }
}
