package services

import (
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/repositories"
)

type WishService interface {
	CreateWish(post *models.Wish) error
	DeleteWish(id uint) error
	UpdateAllWishes(status string) error
	UpdateWish(id uint, status string) error
	GetWishByID(id uint) (*models.Wish, error)
	GetAllWishes(status string, page, pageSize int) ([]models.Wish, error)
	WishExists(id uint) (bool, error)
}

type wishService struct {
	repo repositories.WishRepository
}

func NewWishService(repo repositories.WishRepository) WishService {
	return &wishService{repo: repo}
}

// 1. 등록
func (s *wishService) CreateWish(wish *models.Wish) error {
	return s.repo.Create(wish)
}

// 2. 삭제
func (s *wishService) DeleteWish(id uint) error {
	return s.repo.Delete(id)
}

// 3. 전체 승인/거절
func (s *wishService) UpdateAllWishes(status string) error {
	return s.repo.UpdateAll(status)
}

// 4. 개별 승인/거절
func (s *wishService) UpdateWish(id uint, status string) error {
	return s.repo.UpdateOne(id, status)
}

// 5. 단일 조회
func (s *wishService) GetWishByID(id uint) (*models.Wish, error) {
	return s.repo.FindByID(id)
}

// 6. 목록 조회
func (s *wishService) GetAllWishes(status string, page, pageSize int) ([]models.Wish, error) {
	return s.repo.FindAll(status, page, pageSize)
}

// 소원 존재 여부 확인
func (s *wishService) WishExists(id uint) (bool, error) {
	return s.repo.ExistsByID(id)
}