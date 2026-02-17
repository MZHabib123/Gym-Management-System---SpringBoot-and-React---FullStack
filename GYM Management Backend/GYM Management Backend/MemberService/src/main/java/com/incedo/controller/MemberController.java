package com.incedo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.dto.MemberRequestDTO;
import com.incedo.dto.MemberResponseDTO;
import com.incedo.service.MemberService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 🔹 Create Member
    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(
            @Valid @RequestBody MemberRequestDTO dto) {

        MemberResponseDTO response = memberService.createMember(dto);
        return ResponseEntity.ok(response);
    }

    // 🔹 Get Member By ID
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(
            @PathVariable Long id) {

        MemberResponseDTO response = memberService.getMember(id);
        return ResponseEntity.ok(response);
    }

    // 🔹 Get All Members
    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers() {

        List<MemberResponseDTO> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // 🔹 Update Member
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody MemberRequestDTO dto) {

        MemberResponseDTO updated = memberService.updateMember(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Delete Member
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {

        memberService.deleteMember(id);
        return ResponseEntity.ok("Member deleted successfully");
    }
}
