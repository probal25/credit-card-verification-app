package ws.probal.creditcardverification.domain.events;


import lombok.*;
import ws.probal.creditcardverification.domain.dto.VerificationDetail;
import ws.probal.creditcardverification.domain.enums.EventType;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCreditCardEvent {
    private EventType eventType = EventType.APPROVE_CREDIT_CARD;
    private List<VerificationDetail> verificationDetails;
}
