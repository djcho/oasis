package com.oasis.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invite_links")
public class InviteLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private LocalDateTime expirationTime;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private MemberEntity createdBy;
}
