package ws.probal.creditcardverification.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ws.probal.creditcardverification.domain.dto.ApplicationDetail;
import ws.probal.creditcardverification.domain.dto.VerificationDetail;
import ws.probal.creditcardverification.domain.entity.CreditCardApplication;
import ws.probal.creditcardverification.domain.enums.ApplicationStatusEnum;
import ws.probal.creditcardverification.domain.events.NewCreditCardEvent;
import ws.probal.creditcardverification.domain.events.VerifyCreditCardEvent;
import ws.probal.creditcardverification.repository.CreditCardApplicationRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardVerificationService {

    private final CreditCardApplicationRepository creditCardApplicationRepository;
    public VerifyCreditCardEvent verifyCreditCardApplication(NewCreditCardEvent newCreditCardEvent) {
        List<ApplicationDetail> applicationDetails = newCreditCardEvent.getApplicationDetails();
        List<VerificationDetail> verificationDetails = applicationDetails.stream().map(applicationDetail -> {
            VerificationDetail verificationDetail = VerificationDetail.builder().build();
            BeanUtils.copyProperties(applicationDetail, verificationDetail);
            if (applicationDetail.getAnnualIncome().compareTo(BigDecimal.valueOf(4000)) > 0) {
                verificationDetail.setStatus(ApplicationStatusEnum.APPROVED.getCode());
            } else {
                verificationDetail.setStatus(ApplicationStatusEnum.REJECTED.getCode());
            }
            return verificationDetail;
        }).toList();

        updateCreditCardApplicationStatus(verificationDetails);

        List<VerificationDetail> verifiedApplications = verificationDetails
                .stream()
                .filter(verificationDetail ->
                        verificationDetail.getStatus().equals(ApplicationStatusEnum.APPROVED.getCode()))
                .toList();

        return VerifyCreditCardEvent.builder().verificationDetails(verifiedApplications).build();
    }

    private void updateCreditCardApplicationStatus(List<VerificationDetail> verificationDetails) {
        for (VerificationDetail verificationDetail : verificationDetails) {
            CreditCardApplication creditCardApplication = creditCardApplicationRepository
                    .findByReferenceId(verificationDetail.getReferenceId()).orElse(null);

            if (Objects.isNull(creditCardApplication)) {
                log.error("Can not find any application with reference id : {}", verificationDetail.getReferenceId());
            } else {
                creditCardApplication.setStatus(verificationDetail.getStatus());
                creditCardApplicationRepository.save(creditCardApplication);
            }
        }
    }
}
