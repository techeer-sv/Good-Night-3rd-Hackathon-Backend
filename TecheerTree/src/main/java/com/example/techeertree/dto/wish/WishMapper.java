package com.example.techeertree.dto.wish;

import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.Wish;
import com.example.techeertree.dto.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WishMapper extends EntityMapper<WishRequestDto, WishResponseDto, Wish> {

    WishMapper INSTANCE = Mappers.getMapper(WishMapper.class);

    @Override
    default Wish toEntity(WishRequestDto wishRequestDto){
        if (wishRequestDto == null) {
            return null;
        }
        return Wish.builder()
                .title(wishRequestDto.getTitle())
                .content(wishRequestDto.getContent())
                .category(wishRequestDto.getCategory())
                .isConfirm(Confirm.PENDING)
                .build();
    } ;

    @Override
    WishResponseDto toDto(final Wish wish);

}
