package services

import (
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/repositories"
)

type CommentService interface {
	CreateComment(comment *models.Comment) error
	GetCommentsByWishID(wishID uint, page, pageSize int) ([]models.Comment, error)
	DeleteComment(id uint) error
}

type commentService struct {
	repo repositories.CommentRepository
}

func NewCommentService(repo repositories.CommentRepository) CommentService {
	return &commentService{repo: repo}
}

// 1. 등록
func (s *commentService) CreateComment(comment *models.Comment) error {
	return s.repo.Create(comment)
}

// 2. 조회
func (s *commentService) GetCommentsByWishID(wishID uint, page, pageSize int) ([]models.Comment, error) {
	return s.repo.FindByWishID(wishID, page, pageSize)
}

// 3. 삭제
func (s *commentService) DeleteComment(id uint) error {
	return s.repo.Delete(id)
}