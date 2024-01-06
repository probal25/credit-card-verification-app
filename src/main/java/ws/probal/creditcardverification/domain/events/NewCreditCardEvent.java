package ws.probal.creditcardverification.domain.events;


import lombok.*;

import ws.probal.creditcardverification.domain.dto.ApplicationDetail;
import ws.probal.creditcardverification.domain.enums.EventType;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCreditCardEvent {
    private EventType eventType = EventType.NEW_CREDIT_CARD;
    private List<ApplicationDetail> applicationDetails;
}
