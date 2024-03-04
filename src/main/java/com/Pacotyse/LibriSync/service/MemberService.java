package com.Pacotyse.LibriSync.service;

import com.Pacotyse.LibriSync.exception.DuplicateException;
import com.Pacotyse.LibriSync.exception.NotFoundException;
import com.Pacotyse.LibriSync.model.Member;
import com.Pacotyse.LibriSync.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Registers a new member.
     *
     * @param newMember The member to register.
     * @throws DuplicateException If a member with the same email already exists.
     */
    public void registerMember(Member newMember) {
        Member existingMember = memberRepository.findByEmail(newMember.getEmail());
        if (existingMember != null) {
            throw new DuplicateException();
        }
        memberRepository.save(newMember);
    }

    /**
     * Retrieves all members.
     *
     * @return A list of all members.
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * Retrieves a specific member by ID.
     *
     * @param id The ID of the member to retrieve.
     * @return The member with the specified ID.
     * @throws NotFoundException If the member with the specified ID is not found.
     */
    public Member getOneMember(Long id) {
        return memberRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    /**
     * Updates the details of a specific member.
     *
     * @param id            The ID of the member to update.
     * @param updatedMember The updated details of the member.
     * @return The updated member.
     * @throws NotFoundException If the member with the specified ID is not found.
     */
    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        existingMember.setFirst_name(updatedMember.getFirst_name());
        existingMember.setLast_name(updatedMember.getLast_name());
        existingMember.setEmail(updatedMember.getEmail());
        return memberRepository.save(existingMember);
    }

    /**
     * Deletes a specific member by ID.
     *
     * @param id The ID of the member to delete.
     */
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
