package com.Pacotyse.LibriSync.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Represents a member in the library system.
 */
@Entity
@Data
@Table(name = "member")
public class Member {

    /**
     * The unique identifier for the member.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the member.
     */
    @Column(name = "first_name")
    @NotNull
    private String first_name;

    /**
     * The last name of the member.
     */
    @NotNull
    @Column(name = "last_name")
    private String last_name;

    /**
     * The email address of the member.
     */
    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;
}
