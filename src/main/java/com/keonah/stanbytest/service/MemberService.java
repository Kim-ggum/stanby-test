package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MemberCreateDTO;
import com.keonah.stanbytest.dto.MemberUpdateDTO;
import com.keonah.stanbytest.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    void createMember(MemberCreateDTO memberCreateDto);
    void updateMember(String no, MemberUpdateDTO memberUpdateDto);
    void deleteMember(String no);

    List<MemberEntity> memberList();

    boolean checkNameAndTeamDuplication(String name, String team);
}
