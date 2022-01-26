package com.example.studyorder.domain.partner;



import com.example.studyorder.common.util.TokenGenerator;
import com.example.studyorder.domain.AbstractEntity;
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
@Table(name = "partners")
@NoArgsConstructor
public class Partner extends AbstractEntity {

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

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    public void enable() {
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }

    @Builder
    public Partner(String partnerName, String businessNo, String email,
        Status status) {
        if(StringUtils.isEmpty(businessNo)) throw new IllegalArgumentException("businessNo 빈 값입니다.");
        if(StringUtils.isEmpty(partnerName)) throw new IllegalArgumentException("partnerName 빈 값입니다.");
        if(StringUtils.isEmpty(email)) throw new IllegalArgumentException("email 빈 값입니다.");

        this.partnerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER);
        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
        this.status = status;
    }
}
