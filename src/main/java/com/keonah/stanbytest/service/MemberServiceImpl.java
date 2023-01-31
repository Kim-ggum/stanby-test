package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MemberCreateDTO;
import com.keonah.stanbytest.dto.MemberUpdateDTO;
import com.keonah.stanbytest.entity.MemberEntity;
import com.keonah.stanbytest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private MemberEntity dtoToEntity(MemberCreateDTO memberCreateDto) {

        MemberEntity memberEntity = MemberEntity.builder()
                    .no(memberCreateDto.getNo())
                    .name(memberCreateDto.getName())
                    .position(memberCreateDto.getPosition())
                    .team(memberCreateDto.getTeam())
                    .joinDate(memberCreateDto.getJoinDate())
                    .build();

        return memberEntity;
    }

    @Override
    public void createMember(MemberCreateDTO memberCreateDto) {

        MemberEntity memberEntity = dtoToEntity(memberCreateDto);

        memberRepository.save(memberEntity);

    }

    @Override
    public void updateMember(String no, MemberUpdateDTO memberUpdateDto) {

        MemberEntity memberEntity = MemberEntity.builder()
                .no(no)
                .name(memberUpdateDto.getName())
                .position(memberUpdateDto.getPosition())
                .team(memberUpdateDto.getTeam())
                .joinDate(memberRepository.findByNo(no).getJoinDate())
                .build();

        // 사원 번호와 입사일 수정 불가능하게

       memberRepository.save(memberEntity);
    }

    @Override
    public void deleteMember(String no) {
        memberRepository.delete(memberRepository.findByNo(no));
    }

    @Override
    public List<MemberEntity> memberList() {
        return memberRepository.findAll(Sort.by("no"));
    }

    @Override
    public boolean checkNameAndTeamDuplication(String name, String team) {
        return  memberRepository.existsByNameAndTeam(name, team);
    }
}
