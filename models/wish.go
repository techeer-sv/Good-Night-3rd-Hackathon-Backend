package models

import (
	"errors"
	"time"

	"gorm.io/gorm"
)

type Wish struct {
	gorm.Model
	ID        int       `json:"id" gorm:"primaryKey"`
	Title     string    `json:"title" gorm:"not null"`
	Content   string    `json:"content" gorm:"not null"`
	Category  string    `json: "category" gorm: "size:255;not null"`
	IsConfirm bool      `json: "is_confirm"`
	CreatedAt time.Time `json:"created_at"`
}

// 유효성 검증
func (w *Wish) Validate() error {
	if w.Title == "" || w.Content == "" || w.Category == "" {
		return errors.New("제목, 내용, 카테고리 값은 필수입니다")
	}

	validCategories := []string{"진로", "건강", "인간 관계", "돈", "목표", "학업/성적", "기타"}
	isValidCategory := false
	for _, category := range validCategories {
		if w.Category == category {
			isValidCategory = true
			break
		}
	}

	if !isValidCategory {
		return errors.New("유효한 카테고리가 아닙니다")
	}

	return nil
}
