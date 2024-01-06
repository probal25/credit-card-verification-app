package ws.probal.creditcardverification.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {
    NEW_CREDIT_CARD,
    APPROVE_CREDIT_CARD,
    ;
}
