package repositories

import (
	"errors"

	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"gorm.io/gorm"
)

type WishRepository interface {
	Create(post *models.Wish) error
	Delete(id uint) error
	UpdateAll(status string) error
	UpdateOne(id uint, status string) error
	FindByID(id uint) (*models.Wish, error)
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

// 3. 전체 승인/거절
func (r *wishRepository) UpdateAll(status string) error {
	if status != string(models.Approved) && status != string(models.Rejected) {
		return errors.New("invalid status")
	}
	
	// 상태 업데이트
	return r.db.Model(&models.Wish{}).
		Where("is_confirm = ?", string(models.Pending)).
		Update("is_confirm", status).Error
}

// 4. 개별 승인/거절
func (r *wishRepository) UpdateOne(id uint, status string) error {
	if status != string(models.Approved) && status != string(models.Rejected) {
		return errors.New("invalid status")
	}
	
	var wish models.Wish
	err := r.db.First(&wish, id).Error
	if err != nil {
		return err
	}

	wish.IsConfirm = models.WishStatus(status)
	return r.db.Save(&wish).Error
}

// 5. 단일 조회
func (r *wishRepository) FindByID(id uint) (*models.Wish, error) {
	var wish models.Wish
	err := r.db.Where("id = ? AND is_confirm = '승인' AND deleted_at IS NULL", id).First(&wish).Error
	if err != nil {
		return nil, err
	}
	return &wish, nil
}