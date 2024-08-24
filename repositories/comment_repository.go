package repositories

import (
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"gorm.io/gorm"
)

type CommentRepository interface {
	Create(comment *models.Comment) error
	FindByWishID(wishID uint, page, pageSize int) ([]models.Comment, error)
	Delete(id uint) error
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

// 2. 조회
func (r *commentRepository) FindByWishID(wishID uint, page, pageSize int) ([]models.Comment, error) {
	var comments []models.Comment
	err := r.db.Where("wish_id = ? AND deleted_at IS NULL", wishID).
		Order("created_at DESC").
		Offset((page - 1) * pageSize).
		Limit(pageSize).
		Find(&comments).Error
		
	return comments, err
}

// 3. 삭제
func (r *commentRepository) Delete(id uint) error {
	var comment models.Comment
	if err := r.db.First(&comment, id).Error; err != nil {
		return err
	}
	return r.db.Delete(&comment).Error
}