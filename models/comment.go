package models

import (
	"time"

	"gorm.io/gorm"
)

type Comment struct {
	gorm.Model
	ID        int       `json:"id" gorm:"primaryKey"`
	Content   string    `json:"content"`
	WishID    uint      `json: "wish_id"`
	Wish      Wish      `json: "wish" gorm:"foreignKey:WishID"`
	CreatedAt time.Time `json: "created_at"`
}

type CommentLimit struct {
	Limit int `json:"limit" form:"limit"`
	Page  int `json:"page" form:"page"`
}
