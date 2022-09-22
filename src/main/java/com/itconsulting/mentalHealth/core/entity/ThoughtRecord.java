package com.itconsulting.mentalHealth.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "thought_record")
@Data
public class ThoughtRecord extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "situation", nullable = false)
    private String situation;

    @Lob
    @Column(name = "thoughts", nullable = false)
    private String thoughts;

    @Lob
    @Column(name = "actions", nullable = false)
    private String actions;
    @Lob
    @Column(name = "tipForFriend", nullable = false)
    private String tipForFriend;

    @ElementCollection
    private List<String> moodsFelt = new ArrayList<String>();

    @Column(name = "createdAt", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DAOUser user;
}
