package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.api.RabbitRouterService;
import liga.medical.medicalmonitoring.core.config.RabbitConfig;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RabbitRouterListener {
    private final RabbitRouterService rabbitRouterService;

    @RabbitListener(queues = RabbitConfig.ROUTER)
    public void receiveAndRedirectMessage(String message) {
        rabbitRouterService.routeMessage(message);
    }
}
