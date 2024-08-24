package repositories

import (
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"gorm.io/gorm"
)

type WishRepository interface {
	Create(post *models.Wish) error
}

type wishRepository struct {
	db *gorm.DB
}

func NewWishRepository(db *gorm.DB) WishRepository {
	return &wishRepository{db: db}
}

// 1. 등록
func (r *wishRepository) Create(post *models.Wish) error {
	return r.db.Create(post).Error