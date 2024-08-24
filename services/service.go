package services

import "github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/repositories"

// Service groups all individual services.
type Service struct {
	UserService    UserService
	PostService    PostService
	WishService    WishService
	CommnetService CommentService
}

// NewService creates a new instance of Service with all required services.
func NewService(repo *repositories.Repository) *Service {
	return &Service{
		UserService:    NewUserService(repo.UserRepository),
		PostService:    NewPostService(repo.PostRepository),
		WishService:    NewWishService(repo.WishRepository),
		CommnetService: NewCommentService(repo.CommentRepository),
	}
}
