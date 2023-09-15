package com.dinger.squirrel.infrastructure;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "hello")
@NoArgsConstructor
@AllArgsConstructor
public class HelloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "modified_at")
    protected LocalDateTime modifiedAt;

    @PrePersist
    void created() { this.createdAt = LocalDateTime.now(); }

    @PreUpdate
    void updated() { this.modifiedAt = LocalDateTime.now(); }
}
