package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MemberCreateDto;
import com.keonah.stanbytest.dto.MemberUpdateDto;
import com.keonah.stanbytest.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    void createMember(MemberCreateDto memberCreateDto);
    void updateMember(String no, MemberUpdateDto memberUpdateDto);
    void deleteMember(String no);

    List<MemberEntity> memberList();

    boolean checkNameAndTeamDuplication(String name, String team);
}
