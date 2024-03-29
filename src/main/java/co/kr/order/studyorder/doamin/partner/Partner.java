package co.kr.order.studyorder.doamin.partner;

import co.kr.order.studyorder.common.exception.InvalidParamException;
import co.kr.order.studyorder.common.util.TokenGenerator;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Entity
@Getter
@NoArgsConstructor
@Table(name ="partner")
public class Partner extends AbstractEntity{

    private static final String PREFIX_PARTNER = "ptn_";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Builder
    public Partner(String partnerName, String businessNo, String email) {
        if (StringUtils.isEmpty(partnerName)) throw new InvalidParamException("empty partnerName");
        if (StringUtils.isEmpty(businessNo)) throw new InvalidParamException("empty businessNo");
        if (StringUtils.isEmpty(email)) throw new InvalidParamException("empty email");

        this.partnerName = partnerName;
        this.partnerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER);
        this.businessNo = businessNo;
        this.email = email;
        this.status = Status.DISABLE;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"), DISABLE("비활성화");
        private final String description;
    }

    public void enable(){
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }
}
