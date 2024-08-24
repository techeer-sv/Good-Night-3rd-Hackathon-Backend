package repositories

import (
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"gorm.io/gorm"
)

type WishRepository interface {
	Create(post *models.Wish) error
	Delete(id uint) error
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
}

// 2. 삭제
func (r *wishRepository) Delete(id uint) error {
	var wish models.Wish
	err := r.db.First(&wish, id).Error
	if err != nil {
		return err
	}
	return r.db.Delete(&wish).Error
}