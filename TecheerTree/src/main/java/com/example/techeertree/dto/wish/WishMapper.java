package com.example.techeertree.dto.wish;

import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.WishEntity;
import com.example.techeertree.dto.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import static com.example.techeertree.dto.wish.WishRequestDto.*;
import static com.example.techeertree.dto.wish.WishResponseDto.*;


public interface WishMapper {

    @Mapper
    interface WishCreateMapper extends EntityMapper<WishCreateRequestDto, WishInfoResponseDto, WishEntity> {

        WishCreateMapper INSTANCE = Mappers.getMapper(WishCreateMapper.class);

        @Override
        default WishEntity toEntity(WishCreateRequestDto wishRequestDto) {
            if (wishRequestDto == null) {
                return null;
            }
            return WishEntity.builder()
                    .title(wishRequestDto.getTitle())
                    .content(wishRequestDto.getContent())
                    .category(wishRequestDto.getCategory())
                    .isConfirm(Confirm.PENDING)
                    .build();
        }

    }

    @Mapper
    interface WishUpdateMapper extends EntityMapper<Void, WishUpdateResponseDto, WishEntity> {
        WishUpdateMapper INSTANCE = Mappers.getMapper(WishUpdateMapper.class);

    }

    @Mapper
    interface WishReadMapper {
        WishReadMapper INSTANCE = Mappers.getMapper(WishReadMapper.class);

        WishListResponseDto toDto(WishEntity wishEntity);
    }

}
