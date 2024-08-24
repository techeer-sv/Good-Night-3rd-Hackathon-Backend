package services

import (
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/repositories"
)

type WishService interface {
	CreateWish(post *models.Wish) error
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