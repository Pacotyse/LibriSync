package com.Pacotyse.LibriSync.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a member in the library system.
 */
@Entity
public class Member {

    /**
     * The unique identifier for the member.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The first name of the member.
     */
    @NotNull
    @Column(name = "first_name")
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

    /**
     * Default constructor.
     */
    public Member() {}

    /**
     * Constructs a member with the given parameters.
     * @param first_name The first name of the member.
     * @param last_name The last name of the member.
     * @param email The email address of the member.
     */
    public Member(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    /**
     * Gets the id of the member.
     * @return The id of the member.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the member.
     * @param id The id of the member.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the member.
     * @return The first name of the member.
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets the first name of the member.
     * @param first_name The first name of the member.
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Gets the last name of the member.
     * @return The last name of the member.
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets the last name of the member.
     * @param last_name The last name of the member.
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Gets the email address of the member.
     * @return The email address of the member.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the member.
     * @param email The email address of the member.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
