package com.Pacotyse.LibriSync.controller;

import com.Pacotyse.LibriSync.exception.DuplicateException;
import com.Pacotyse.LibriSync.exception.NotFoundException;
import com.Pacotyse.LibriSync.model.Member;
import com.Pacotyse.LibriSync.repository.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller class for managing members in the library system.
 */
@RestController
public class MemberController {

    private final MemberRepository repository;

    /**
     * Constructor for MemberController.
     * @param repository The repository for managing members.
     */
    MemberController(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all members.
     * @return A collection of member resources.
     */
    @GetMapping("/members")
    CollectionModel<EntityModel<Member>> all() {
        List<EntityModel<Member>> members = repository.findAll().stream()
                .map(member -> EntityModel.of(member,
                        linkTo(methodOn(MemberController.class).one(member.getId())).withSelfRel(),
                        linkTo(methodOn(MemberController.class).all()).withRel("members")))
                .collect(Collectors.toList());

        return CollectionModel.of(members, linkTo(methodOn(MemberController.class).all()).withSelfRel());
    }

    /**
     * Creates a new member.
     * @param newMember The member to be created.
     * @return The created member.
     */
    @PostMapping("/members")
    public Member newUser(@Valid @RequestBody Member newMember) {
        return repository.save(newMember);
    }

    /**
     * Retrieves a specific member by ID.
     * @param id The ID of the member to retrieve.
     * @return The member resource.
     * @throws NotFoundException if the member with the specified ID is not found.
     */
    @GetMapping("/members/{id}")
    EntityModel<Member> one(@PathVariable Long id) {
        Member member = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        return EntityModel.of(member,
                linkTo(methodOn(MemberController.class).one(id)).withSelfRel(),
                linkTo(methodOn(MemberController.class).all()).withRel("members"));
    }

    /**
     * Replaces an existing member with new information.
     * @param newMember The new information for the member.
     * @param id The ID of the member to replace.
     * @return The updated member.
     */
    @PutMapping("/members/{id}")
    Member replaceMember(@RequestBody Member newMember, @PathVariable Long id) {
        return repository.findById(id)
                .map(member -> {
                    member.setFirst_name(newMember.getFirst_name());
                    member.setLast_name(newMember.getLast_name());
                    member.setEmail(newMember.getEmail());
                    return repository.save(member);
                })
                .orElseGet(() -> {
                    newMember.setId(id);
                    return repository.save(newMember);
                });
    }

    /**
     * Deletes a member by ID.
     * @param id The ID of the member to delete.
     */
    @DeleteMapping("/members/{id}")
    void deleteMember(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
