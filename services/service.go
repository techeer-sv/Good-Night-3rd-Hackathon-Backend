package services

import "github.com/hoon99/Good-Night-3rd-Hackathon-Backend/repositories"

type Service struct {
	WishService WishService
	CommentService CommentService
}

func NewService(repo *repositories.Repository) *Service {
	return &Service{
		WishService: NewWishService(repo.WishRepository),
		CommentService: NewCommentService(repo.CommentRepository),
	}
}
