package services

import (
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/models"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/repositories"
)

type WishService interface {
	RegisterWish(wish *models.Wish) error
	DeleteWish(id uint) error
	ApproveWish(id uint) error
	RejectWish(id uint) error
	GetWish(id uint) (*models.Wish, error)
	GetAllWishes(status string, page int, pageSize int) ([]*models.Wish, int64, error)
}

type wishService struct {
	repo repositories.WishRepository
}

func NewWishService(repo repositories.WishRepository) WishService {
	return &wishService{repo: repo}
}

func (s *wishService) RegisterWish(wish *models.Wish) error {
	if err := wish.Validate(); err != nil {
		return err
	}
	return s.repo.CreateWish(wish)
}

func (s *wishService) DeleteWish(id uint) error {
	return s.repo.DeleteWish(id)
}

func (s *wishService) ApproveWish(id uint) error {
	return s.repo.UpdateWish(id, "승인됨")
}

func (s *wishService) RejectWish(id uint) error {
	return s.repo.UpdateWish(id, "거절됨")
}

func (s *wishService) GetWish(id uint) (*models.Wish, error) {
	return s.repo.GetWish(id)
}

func (s *wishService) GetAllWishes(status string, page int, pageSize int) ([]*models.Wish, int64, error) {
	return s.repo.GetAllWishes(status, page, pageSize)
}
