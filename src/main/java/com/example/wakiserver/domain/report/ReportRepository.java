package com.example.wakiserver.domain.report;

import com.example.wakiserver.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findFirstByUserOrderByReportIdxDesc(User user);
}
