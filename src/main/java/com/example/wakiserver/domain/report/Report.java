package com.example.wakiserver.domain.report;

import com.example.wakiserver.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long reportIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;

    @CreationTimestamp
    private Timestamp createTime;

    private String mode;

    private Double avgSpeed;

    private Double topSpeed;

    @Builder
    public Report(User user, String mode, Double avgSpeed, Double topSpeed){
        this.user = user;
        this.mode = mode;
        this.avgSpeed = avgSpeed;
        this.topSpeed = topSpeed;
    }


}
