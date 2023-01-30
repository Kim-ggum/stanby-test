package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MemberCreateDto;
import com.keonah.stanbytest.dto.MemberUpdateDto;
import com.keonah.stanbytest.entity.MemberEntity;
import com.keonah.stanbytest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private MemberEntity dtoToEntity(MemberCreateDto memberCreateDto) {

        MemberEntity memberEntity = MemberEntity.builder()
                    .id(memberCreateDto.getId())
                    .name(memberCreateDto.getName())
                    .position(memberCreateDto.getPosition())
                    .team(memberCreateDto.getTeam())
                    .joinDate(memberCreateDto.getJoinDate())
                    .build();

        return memberEntity;
    }

    @Override
    public void createMember(MemberCreateDto memberCreateDto) {

        MemberEntity memberEntity = dtoToEntity(memberCreateDto);

        memberRepository.save(memberEntity);

    }

    @Override
    public void updateMember(Long no, MemberUpdateDto memberUpdateDto) {

        MemberEntity memberEntity = MemberEntity.builder()
                .no(no)
                .id(memberRepository.findByNo(no).getId())
                .name(memberUpdateDto.getName())
                .position(memberUpdateDto.getPosition())
                .team(memberUpdateDto.getTeam())
                .joinDate(memberRepository.findByNo(no).getJoinDate())
                .build();

        // 사원 번호와 입사일 수정 불가능하게

       memberRepository.save(memberEntity);
    }

    @Override
    public boolean checkNameAndTeamDuplication(String name, String team) {
        return  memberRepository.existsByNameAndTeam(name, team);
    }
}
