package repositories

import (
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"gorm.io/gorm"
)

type CommentRepository interface {
	Create(comment *models.Comment) error
}

type commentRepository struct {
	db *gorm.DB
}

func NewCommentRepository(db *gorm.DB) CommentRepository {
	return &commentRepository{db: db}
}

// 1. 생성
func (r *commentRepository) Create(comment *models.Comment) error {
	return r.db.Create(comment).Error
}
