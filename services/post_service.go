package services

import (
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/models"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/repositories"
)

type PostService interface {
	GetPostByID(id uint) (*models.Post, error)
	CreatePost(post *models.Post) error
}

type postService struct {
	repo repositories.PostRepository
}

func NewPostService(repo repositories.PostRepository) PostService {
	return &postService{repo: repo}
}

func (s *postService) GetPostByID(id uint) (*models.Post, error) {
	return s.repo.FindByID(id)
}

func (s *postService) CreatePost(post *models.Post) error {
	return s.repo.Create(post)
}
