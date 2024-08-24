package models

import (
	"gorm.io/gorm"
)

type Comment struct {
	gorm.Model
	ID      int    `json:"id" gorm:"primaryKey"`
	Content string `json:"content"`
	WishID  uint   `json: "wish_id"`
	Wish    Wish   `json: "wish" gorm:"foreignKey:WishID"`
}
