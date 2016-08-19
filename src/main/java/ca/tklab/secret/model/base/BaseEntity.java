package ca.tklab.secret.model.base;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Petri Kainulainen
 */
@MappedSuperclass
public abstract class BaseEntity<ID> {

    @Column(name = "creation_time", nullable = false)
//    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Temporal(TemporalType.DATE)
    private  java.util.Calendar creationTime;

    @Column(name = "modification_time", nullable = false)
//    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Temporal(TemporalType.DATE)
    private  java.util.Calendar modificationTime;



    public abstract ID getId();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public  Calendar getCreationTime() {
        return creationTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public  Calendar getModificationTime() {
        return modificationTime;
    }



    @PrePersist
    public void prePersist() {
    	Calendar now = Calendar.getInstance();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = Calendar.getInstance();
    }
}


