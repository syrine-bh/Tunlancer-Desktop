/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Hiba
 */

public class Participation {

   

 
private int id;
private Concour concour_id;
private Video video_id;
private Timestamp dateParticipation;
private Users userId;

    public Participation() {
    }

    public Participation(Timestamp dateParticipation, Users userId) {
        this.dateParticipation = dateParticipation;
        this.userId = userId;
    }

    public Participation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateParticipation() {
        return dateParticipation;
    }

    public void setDateParticipation(Date dateParticipation) {
        this.dateParticipation = (Timestamp) dateParticipation;
    }

    public Users getUserId() {
        return userId;
    }
    public Concour getConcourId() {
        return concour_id;
    }
 public void seConcourId(Concour concour_id) {
        this.concour_id = concour_id;
    }
    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Participation(Concour concour_id, Video video_id, Timestamp dateParticipation, Users userId) {
        this.concour_id = concour_id;
        this.video_id = video_id;
        this.dateParticipation = dateParticipation;
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.concour_id);
        hash = 53 * hash + Objects.hashCode(this.video_id);
        hash = 53 * hash + Objects.hashCode(this.dateParticipation);
        hash = 53 * hash + Objects.hashCode(this.userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Participation other = (Participation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.concour_id, other.concour_id)) {
            return false;
        }
        if (!Objects.equals(this.video_id, other.video_id)) {
            return false;
        }
        if (!Objects.equals(this.dateParticipation, other.dateParticipation)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", concour_id=" + concour_id + ", video_id=" + video_id + ", dateParticipation=" + dateParticipation + ", userId=" + userId + '}';
    }

    




    
    
}
