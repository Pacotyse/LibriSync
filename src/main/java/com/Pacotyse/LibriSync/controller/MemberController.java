package com.Pacotyse.LibriSync.controller;

import com.Pacotyse.LibriSync.model.Member;
import com.Pacotyse.LibriSync.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller class for managing members in the library system.
 */
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * Registers a new member.
     *
     * @param newMember The member to register.
     * @return The created member.
     */
    @PostMapping("register")
    ResponseEntity<Member> registerMember(@Valid @RequestBody Member newMember) {
        memberService.registerMember(newMember);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    /**
     * Retrieves all members.
     *
     * @return A collection of member resources.
     */
    @GetMapping("")
    CollectionModel<EntityModel<Member>> getAllMembers() {
        List<EntityModel<Member>> members = memberService.getAllMembers().stream()
                .map(member -> EntityModel.of(member,
                        linkTo(methodOn(MemberController.class).getOneMember(member.getId())).withSelfRel(),
                        linkTo(methodOn(MemberController.class).getAllMembers()).withRel("members")))
                .collect(Collectors.toList());

        return CollectionModel.of(members, linkTo(methodOn(MemberController.class).getAllMembers()).withSelfRel());
    }

    /**
     * Retrieves a specific member by ID.
     *
     * @param id The ID of the member to retrieve.
     * @return The member with the specified ID.
     */
    @GetMapping("/{id}")
    EntityModel<Member> getOneMember(@PathVariable Long id) {
        return EntityModel.of(memberService.getOneMember(id),
                linkTo(methodOn(MemberController.class).getOneMember(id)).withSelfRel(),
                linkTo(methodOn(MemberController.class).getAllMembers()).withRel("members"));
    }

    /**
     * Updates the details of a specific member.
     *
     * @param id            The ID of the member to update.
     * @param updatedMember The updated details of the member.
     * @return The updated member.
     */
    @PutMapping("{id}")
    ResponseEntity<Member> updateMember(@PathVariable Long id, @Valid @RequestBody Member updatedMember) {
        Member member = memberService.updateMember(id, updatedMember);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * Deletes a specific member by ID.
     *
     * @param id The ID of the member to delete.
     * @return HTTP status indicating the success of the operation.
     */
    @DeleteMapping("{id}")
    ResponseEntity<Member> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
