package com.Pacotyse.LibriSync.exception;

/**
 * Exception thrown when a member with a specific reference ID is not found.
 */
public class MemberNotFoundException extends RuntimeException {

    /**
     * Constructs a new MemberNotFoundException with the given member reference ID.
     * @param id The reference ID of the member.
     */
    public MemberNotFoundException(Long id) {
        super("Member's reference " + id + " not found.");
    }
}
