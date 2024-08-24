package repositories

import (
	"errors"
	"time"

	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/models"
	"gorm.io/gorm"
)

type WishRepository interface {
	CreateWish(wish *models.Wish) error
	GetWish(id uint) (*models.Wish, error)
	GetAllWishes(status string, page int, pageSize int) ([]*models.Wish, int64, error)
	UpdateWish(id uint, status string) error
	DeleteWish(id uint) error
}

type wishRepository struct {
	db *gorm.DB
}

// CreateWish implements WishRepository.
func (w *wishRepository) CreateWish(wish *models.Wish) error {
	return w.db.Create(wish).Error
}

// DeleteWish implements WishRepository.
func (w *wishRepository) DeleteWish(id uint) error {
	return w.db.Delete(&models.Wish{}).Where("id = ?", id).Update("deleted_at", gorm.DeletedAt{Time: time.Now(), Valid: true}).Error
}

// GetAllWishes implements WishRepository.
func (w *wishRepository) GetAllWishes(status string, page int, pageSize int) ([]*models.Wish, int64, error) {
	var wishes []*models.Wish
	var totalCount int64

	offset := (page - 1) * pageSize
	query := w.db.Where("deleted_at IS NULL")

	if status != "" {
		query = query.Where("is_confirm = ?", status)
	}

	err := query.Model(&models.Wish{}).Count(&totalCount).Error
	if err != nil {
		return nil, 0, err
	}

	err = query.Select("title, category, created_at").Order("created_at desc").Offset(offset).Limit(pageSize).Find(&wishes).Error
	if err != nil {
		return nil, 0, err
	}

	return wishes, totalCount, err
}

// GetWish implements WishRepository.
func (w *wishRepository) GetWish(id uint) (*models.Wish, error) {
	var wish models.Wish
	err := w.db.Where("id = ? AND deleted_at IS NULL", id).First(&wish).Error
	if err != nil {
		return nil, err
	}
	if wish.IsConfirm != "승인됨" {
		return nil, errors.New("wish not approved")
	}
	return &wish, nil
}

// UpdateWish implements WishRepository.
func (w *wishRepository) UpdateWish(id uint, status string) error {
	return w.db.Model(&models.Wish{}).Where("id = ?", id).Update("is_confirm", status).Error
}

func NewWishReposityory(db *gorm.DB) WishRepository {
	return &wishRepository{db: db}
}
