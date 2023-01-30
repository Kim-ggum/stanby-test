package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MemberCreateDto;
import com.keonah.stanbytest.dto.MemberUpdateDto;

public interface MemberService {
    void createMember(MemberCreateDto memberCreateDto);
    void updateMember(MemberUpdateDto memberUpdateDto);

    boolean checkNameAndTeamDuplication(String name, String team);
}
