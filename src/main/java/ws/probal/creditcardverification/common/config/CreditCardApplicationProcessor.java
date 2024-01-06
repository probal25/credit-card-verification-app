package ws.probal.creditcardverification.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.probal.creditcardverification.domain.events.NewCreditCardEvent;
import ws.probal.creditcardverification.domain.events.VerifyCreditCardEvent;
import ws.probal.creditcardverification.service.CreditCardVerificationService;

import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CreditCardApplicationProcessor {

    private final CreditCardVerificationService creditCardVerificationService;

    @Bean
    public Function<NewCreditCardEvent, VerifyCreditCardEvent> verifyCreditCardEventFunction() {
        return newCreditCardEvent -> {
            VerifyCreditCardEvent verifyCreditCardEvent = creditCardVerificationService
                    .verifyCreditCardApplication(newCreditCardEvent);

            log.info("Total records: {}", verifyCreditCardEvent.getVerificationDetails().size());

            return verifyCreditCardEvent;
        };
    }
}
