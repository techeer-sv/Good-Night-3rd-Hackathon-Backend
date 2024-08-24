package services

import (
	"time"

	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/models"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/repositories"
	"gorm.io/gorm"
)

type CommentService interface {
	FindCommentByID(id uint) (*models.Comment, error)
	FindCommentsByWishID(wishID uint, limit models.CommentLimit) ([]*models.Comment, error)
	CreateComment(comment *models.Comment) error
	DeleteComment(id uint) error
}

type commentService struct {
	repo repositories.CommentRepository
}

func NewCommentService(repo repositories.CommentRepository) CommentService {
	return &commentService{repo: repo}
}

func (s *commentService) FindCommentByID(id uint) (*models.Comment, error) {
	return s.repo.FindByID(id)
}

func (s *commentService) FindCommentsByWishID(wishID uint, limit models.CommentLimit) ([]*models.Comment, error) {
	return s.repo.FindByWishID(wishID, limit)
}

func (s *commentService) CreateComment(comment *models.Comment) error {
	return s.repo.CreateComment(comment)
}

func (s *commentService) DeleteComment(id uint) error {
	comment, err := s.repo.FindByID(id)
	if err != nil {
		return err
	}
	comment.DeletedAt = gorm.DeletedAt{Time: time.Now(), Valid: true}
	return s.repo.DeleteComment(comment)
}
