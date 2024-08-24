package models

import (
	"time"

	"gorm.io/gorm"
)

type Comment struct {
	ID        int            `json:"id" gorm:"primaryKey"`
	WishID    uint            `json:"wish_id"`
	Wish      Wish           `json:"-" gorm:"constraint:OnUpdate:CASCADE,OnDelete:SET NULL;"`  // 외래 키 설정
	Content   string         `json:"content" validate:"required"`
	CreatedAt time.Time      `json:"created_at" gorm:"autoCreateTime"`
	DeletedAt gorm.DeletedAt `json:"deleted_at" gorm:"index"`
}

type CommentInput struct {
	WishID  uint   `json:"wish_id" binding:"required"`
	Content string `json:"content" binding:"required"`
}
