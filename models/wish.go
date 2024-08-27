package models

import (
	"time"

	"gorm.io/gorm"
)

type Category string

const (
	Career    		Category = "진로"
	Health   		  Category = "건강"
	Relationships Category = "인간관계"
	Money     		Category = "돈"
	Goals      		Category = "목표"
	Academics  		Category = "학업/성적"
	Other      		Category = "기타"
)

type WishStatus string

const (
	Approved WishStatus = "approved"
	Pending  WishStatus = "pending"
	Rejected WishStatus = "rejected"
)

type Wish struct {
	ID        int            `json:"id" gorm:"primaryKey"`
	Title     string         `json:"title" validate:"required"`
	Content   string         `json:"content" validate:"required"`
	Category  Category       `json:"category" validate:"required"`
	CreatedAt time.Time      `json:"created_at" gorm:"autoCreateTime"`
	IsConfirm WishStatus     `json:"is_confirm" gorm:"default:'pending'"`
	DeletedAt gorm.DeletedAt `json:"deleted_at" gorm:"index"`
}

type WishInput struct {
	Title    string `json:"title" binding:"required"`
	Content  string `json:"content" binding:"required"`
	Category string `json:"category" binding:"required"`
}