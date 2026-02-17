package com.incedo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.incedo.dto.MemberRequestDTO;
import com.incedo.dto.MemberResponseDTO;
import com.incedo.entity.Member;
import com.incedo.entity.Membership;
import com.incedo.entity.Plan;
import com.incedo.exception.MemberNotFoundException;
import com.incedo.exception.PlanNotFoundException;
import com.incedo.repository.MemberRepository;
import com.incedo.repository.PlanRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;

    public MemberService(MemberRepository memberRepository,
                         PlanRepository planRepository) {
        this.memberRepository = memberRepository;
        this.planRepository = planRepository;
    }

    // 🔹 CREATE MEMBER
    public MemberResponseDTO createMember(MemberRequestDTO dto) {

        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() ->
                        new PlanNotFoundException("Plan not found with ID: " + dto.getPlanId()));

        Membership membership = new Membership();
        membership.setStartDate(LocalDate.now());
        membership.setEndDate(LocalDate.now().plusMonths(plan.getDurationInMonths()));
        membership.setStatus("ACTIVE");
        membership.setPlan(plan);

        Member member = new Member();
        member.setFirstName(dto.getFirstName());
        member.setLastName(dto.getLastName());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setMembership(membership);

        Member savedMember = memberRepository.save(member);

        return mapToResponse(savedMember);
    }

    // 🔹 GET MEMBER BY ID
    public MemberResponseDTO getMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("Member not found with ID: " + id));

        return mapToResponse(member);
    }

    // 🔹 GET ALL MEMBERS
    public List<MemberResponseDTO> getAllMembers() {

        List<Member> members = memberRepository.findAll();
        List<MemberResponseDTO> responseList = new ArrayList<>();

        for (Member member : members) {
            responseList.add(mapToResponse(member));
        }

        return responseList;
    }

    // 🔹 UPDATE MEMBER
    public MemberResponseDTO updateMember(Long id, MemberRequestDTO dto) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("Member not found with ID: " + id));

        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() ->
                        new PlanNotFoundException("Plan not found with ID: " + dto.getPlanId()));

        member.setFirstName(dto.getFirstName());
        member.setLastName(dto.getLastName());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());

        Membership membership = member.getMembership();
        membership.setPlan(plan);
        membership.setStartDate(LocalDate.now());
        membership.setEndDate(LocalDate.now().plusMonths(plan.getDurationInMonths()));
        membership.setStatus("ACTIVE");

        Member updatedMember = memberRepository.save(member);

        return mapToResponse(updatedMember);
    }

    // 🔹 DELETE MEMBER
    public void deleteMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("Member not found with ID: " + id));

        memberRepository.delete(member);
    }

    // 🔹 MAPPER METHOD (Entity → DTO)
    private MemberResponseDTO mapToResponse(Member member) {

        MemberResponseDTO dto = new MemberResponseDTO();
        dto.setId(member.getId());
        dto.setFirstName(member.getFirstName());
        dto.setLastName(member.getLastName());
        dto.setEmail(member.getEmail());
        dto.setPhone(member.getPhone());

        if (member.getMembership() != null &&
            member.getMembership().getPlan() != null) {

            dto.setPlanName(member.getMembership().getPlan().getName());
            dto.setMembershipStatus(member.getMembership().getStatus());
        }

        return dto;
    }
}
