package liga.medical.medicalmonitoring.core.config;

import liga.medical.medicalmonitoring.core.model.NameQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {
    public static final String DIRECT_EXCHANGE_NAME = "dima_test";

    private final Queue simpleQueue;
    private final Queue extraQueue;
    private final Queue errorQueue;

    public ExchangeConfig(@Qualifier("DailyQueue") Queue simpleQueue,
                          @Qualifier("AlertQueue") Queue extraQueue,
                          @Qualifier("ErrorQueue") Queue errorQueue) {
        this.simpleQueue = simpleQueue;
        this.extraQueue = extraQueue;
        this.errorQueue = errorQueue;
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingDailyQueue() {
        return BindingBuilder.bind(simpleQueue).to(directExchange()).with(NameQueue.DAILY.toString());
    }

    @Bean
    public Binding bindingAlertQueue() {
        return BindingBuilder.bind(extraQueue).to(directExchange()).with(NameQueue.ALERT.toString());
    }

    @Bean
    public Binding bindingErrorQueue() {
        return BindingBuilder.bind(errorQueue).to(directExchange()).with(NameQueue.ERROR.toString());
    }
}
