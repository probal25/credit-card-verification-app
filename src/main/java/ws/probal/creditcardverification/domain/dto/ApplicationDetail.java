package ws.probal.creditcardverification.domain.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDetail {
    private String firstName;

    private String lastName;

    private BigDecimal annualIncome;

    private String address;

    private String referenceId;
}
