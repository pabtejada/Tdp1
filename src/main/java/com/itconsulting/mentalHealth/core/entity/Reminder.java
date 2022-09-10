package com.itconsulting.mentalHealth.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reminder")
@Data
public class Reminder{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "reminderDate", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date reminderDate;

    @Column(name = "message", nullable = false,length = 256)
    private  String message;
   /* @Column(name = "mondayActive", nullable = false,length = 256)
    private  Boolean mondayActive;
    @Column(name = "tuesdayActive", nullable = false,length = 256)
    private  Boolean tuesdayActive;
    @Column(name = "wednesdayActive", nullable = false,length = 256)
    private  Boolean wednesdayActive;
    @Column(name = "thursdayActive", nullable = false,length = 256)
    private  Boolean thursdayActive;
    @Column(name = "fridayActive", nullable = false,length = 256)
    private  Boolean fridayActive;
    @Column(name = "saturdayActive", nullable = false,length = 256)
    private  Boolean saturdayActive;
    @Column(name = "sundayActive", nullable = false,length = 256)
    private  Boolean sundayActive;
    */
   @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DAOUser user;

}
