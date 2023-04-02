package com.oasis.mapper;

import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.request.CreateMemberRequestDto;
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

    public MemberEntity createMemberRequestDtoToEntity(CreateMemberRequestDto createMemberRequestDto) {
        return modelMapper.map(createMemberRequestDto, MemberEntity.class);
    }

    public MemberDto memberEntityToMemberDto(MemberEntity savedMember) {
        return modelMapper.map(savedMember, MemberDto.class);
    }
}
