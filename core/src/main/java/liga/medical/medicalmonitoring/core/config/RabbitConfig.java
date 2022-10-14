package liga.medical.medicalmonitoring.core.config;

import liga.medical.medicalmonitoring.core.model.NameQueue;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private static final String HOST = "localhost";
    public static final String ROUTER = "common-monitoring";


    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(HOST);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue getQueue() {
        return new Queue(ROUTER);
    }

    @Bean("DailyQueue")
    public Queue getDailyQueue() {
        return new Queue(NameQueue.DAILY.getStr());
    }

    @Bean("AlertQueue")
    public Queue getAlertQueue() {
        return new Queue(NameQueue.ALERT.getStr());
    }

    @Bean("ErrorQueue")
    public Queue getErrorQueue() {
        return new Queue(NameQueue.ERROR.getStr());
    }
}
