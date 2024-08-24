package repositories

import (
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/models"
	"gorm.io/gorm"
)

type CommentRepository interface {
	FindByID(id uint) (*models.Comment, error)
	FindByWishID(wishID uint, limit models.CommentLimit) ([]*models.Comment, error)
	CreateComment(comment *models.Comment) error
	DeleteComment(comment *models.Comment) error
}

type commentRepository struct {
	db *gorm.DB
}

// Create implements CommentRepository.
func (c *commentRepository) CreateComment(comment *models.Comment) error {
	return c.db.Create(comment).Error
}

// Delete implements CommentRepository.
func (c *commentRepository) DeleteComment(comment *models.Comment) error {
	return c.db.Delete(comment).Error
}

// FindByID implements CommentRepository.
func (c *commentRepository) FindByID(id uint) (*models.Comment, error) {
	var comment models.Comment
	err := c.db.First(&comment, id).Error
	return &comment, err
}

// FindByWishID implements CommentRepository.
func (c *commentRepository) FindByWishID(wishID uint, limit models.CommentLimit) ([]*models.Comment, error) {
	var comments []*models.Comment
	err := c.db.Where("wish_id = ?", wishID).Limit(limit.Limit).Offset((limit.Page - 1) * limit.Limit).Find(&comments).Error
	return comments, err
}

func NewCommentRepository(db *gorm.DB) CommentRepository {
	return &commentRepository{db: db}
}
