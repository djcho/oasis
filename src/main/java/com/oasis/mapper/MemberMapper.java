package com.oasis.mapper;

import com.oasis.data.dto.response.GetMemberInfoResponseDto;
import com.oasis.data.entity.MemberEntity;
import com.oasis.service.data.GetMemberInfoResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {
    private final ModelMapper modelMapper;

    public GetMemberInfoResult toGetMemberInfoResult(MemberEntity memberEntity){
        modelMapper.typeMap(MemberEntity.class, GetMemberInfoResult.class)
                .addMappings(mapper -> mapper.map(MemberEntity::getUid, GetMemberInfoResult::setUserId));
        return modelMapper.map(memberEntity, GetMemberInfoResult.class);
    }

    public GetMemberInfoResponseDto toGetMemberInfoResponseDto(GetMemberInfoResult getMemberInfoResult) {
        return modelMapper.map(getMemberInfoResult, GetMemberInfoResponseDto.class);
    }
}
