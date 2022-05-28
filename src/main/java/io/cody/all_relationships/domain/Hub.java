package io.cody.all_relationships.domain;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Hub {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String manyExOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mofhubtx_id")
    private X moFHubTX;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "omfythub_id", nullable = false)
    private Y omFYTHub;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "mm_from_hub_tob",
            joinColumns = @JoinColumn(name = "hub_id"),
            inverseJoinColumns = @JoinColumn(name = "b_id")
    )
    private Set<B> mmFromHubToBBs;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
