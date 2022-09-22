package com.itconsulting.mentalHealth.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ThoughtRecordResource {

    private Long id;
    private String situation;
    private String thoughts;
    private String actions;
    private String tipForFriend;
    private List<String> moodsFelt = new ArrayList<String>();

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date createdAt;
}
