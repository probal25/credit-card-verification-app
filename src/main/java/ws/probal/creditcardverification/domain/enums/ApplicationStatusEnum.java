package ws.probal.creditcardverification.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationStatusEnum {

    PENDING("PENDING", "Application Pending"),
    PUBLISHED("PUBLISHED", "Application Published"),
    APPROVED("APPROVED", "Application Approved"),
    REJECTED("REJECTED", "Application Rejected"),
    ;

    private String code;
    private String description;
}
