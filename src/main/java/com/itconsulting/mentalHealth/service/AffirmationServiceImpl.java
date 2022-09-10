package com.itconsulting.mentalHealth.service;

import com.itconsulting.mentalHealth.core.entity.Affirmation;
import com.itconsulting.mentalHealth.core.repository.AffirmationRepository;
import com.itconsulting.mentalHealth.core.repository.UserRepository;
import com.itconsulting.mentalHealth.core.service.AffirmationService;
import com.itconsulting.mentalHealth.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
@Service
public class AffirmationServiceImpl implements AffirmationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AffirmationRepository affirmationRepository;
    @Override
    public Page<Affirmation> getAllAffirmations(Pageable pageable) {
        return affirmationRepository.findAll(pageable);
    }

    @Override
    public Page<Affirmation> getAllAffirmationsByUserId(Long userId, Pageable pageable) {
        return affirmationRepository.findByUserId(userId,pageable);
    }

    @Override
    public Affirmation getTheDayOfWeek(Long affirmationId, Long userId) {
       Affirmation affirmation =  affirmationRepository.findByIdAndUserId(affirmationId,userId).orElseThrow(() ->new ResourceNotFoundException("Affirmation Not Found with Id"+ affirmationId));

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(affirmation.getCreationDate());
        Date localTime = affirmation.getCreationDate();
        int dayOfWeek = localTime.getDay();
        DayOfWeek dayOfWeek1 = DayOfWeek.of(dayOfWeek);
        //DayOfWeek dayOfWeek1 = DayOfWeek.of(dayOfWeek);
        affirmation.setDayOfTheWeek(String.valueOf(dayOfWeek1));
        return affirmationRepository.save(affirmation);
    }

    @Override
    public Affirmation getAffirmationByIdAndUserId(Long affirmationId, Long userId) {
        return affirmationRepository.findByIdAndUserId(affirmationId,userId).orElseThrow(() ->new ResourceNotFoundException("Affirmation Not Found with Id"+ affirmationId));
    }

    @Override
    public Affirmation updateAffirmationById(Affirmation new_affirmation, Long affirmationId, Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Affirmation old_affirmation = affirmationRepository.findById(affirmationId).orElseThrow(() -> new ResourceNotFoundException("Exercise Registry"));

        old_affirmation.setCreationDate(new_affirmation.getCreationDate());
        old_affirmation.setMessage(new_affirmation.getMessage());

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new_affirmation.getCreationDate());
        Date localTime = new_affirmation.getCreationDate();
        int dayOfWeek = localTime.getDay();
        DayOfWeek dayOfWeek1 = DayOfWeek.of(dayOfWeek);
        //DayOfWeek dayOfWeek1 = DayOfWeek.of(dayOfWeek);
        old_affirmation.setDayOfTheWeek(String.valueOf(dayOfWeek1));

        return affirmationRepository.save(old_affirmation);
    }

    @Override
    public Affirmation saveAffirmation(Affirmation affirmation, Long userId) {
        affirmation.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId)));

        return affirmationRepository.save(affirmation);
    }

    @Override
    public ResponseEntity<?> deleteAffirmation(Long affirmationId, Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Affirmation affirmation = affirmationRepository.findById(affirmationId).orElseThrow(() -> new ResourceNotFoundException("Exercise Registry"));
        affirmationRepository.delete(affirmation);
        return ResponseEntity.ok().build();
    }


}
