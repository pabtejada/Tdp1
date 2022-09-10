package com.itconsulting.mentalHealth.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "goal")
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "message", nullable = false, length=256)
    private String message;
    @Column(name = "startDate", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startDate;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "actionPlan1", nullable = false, length=256)
    private String actionPlan1;
    @Column(name = "actionPlan2", nullable = false, length=256)
    private String actionPlan2;
    @Column(name = "actionPlan3", nullable = false, length=256)
    private String actionPlan3;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DAOUser user;
}
