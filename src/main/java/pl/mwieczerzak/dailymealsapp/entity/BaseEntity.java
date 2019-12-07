package pl.mwieczerzak.dailymealsapp.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date createdOn;

    protected String createdBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    protected Date lastModified;

    protected String modifiedBy;

    protected BaseEntity(String createdBy, String modifiedBy) {
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }
}