package services

import (
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/models"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/repositories"
)

type UserService interface {
	GetUserByID(id uint) (*models.User, error)
	CreateUser(user *models.User) error
}

type userService struct {
	repo repositories.UserRepository
}

func NewUserService(repo repositories.UserRepository) UserService {
	return &userService{repo: repo}
}

func (s *userService) GetUserByID(id uint) (*models.User, error) {
	return s.repo.FindByID(id)
}

func (s *userService) CreateUser(user *models.User) error {
	return s.repo.Create(user)
}
