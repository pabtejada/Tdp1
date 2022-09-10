package com.itconsulting.mentalHealth.core.repository;

import com.itconsulting.mentalHealth.core.entity.MoodTracker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface MoodTrackerRepository extends JpaRepository<MoodTracker,Long> {
    Page<MoodTracker> findByUserId(Long userId, Pageable pageable);

    Page<MoodTracker> findByUserIdAndMoodTrackerDateBetween(Long userId, Date startDate, Date endDate, Pageable pageable);

    Optional<MoodTracker> findByIdAndUserId(Long id, Long userId);
}
