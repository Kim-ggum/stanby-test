package com.keonah.stanbytest.util;

import com.keonah.stanbytest.entity.Role;

import javax.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return role.ordinal();
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        return Role.values()[dbData];
    }

}
