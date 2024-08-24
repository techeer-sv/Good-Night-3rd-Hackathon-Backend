package handlers

import "github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/services"

// Handler groups all individual handlers.
type Handler struct {
	UserHandler    *UserHandler
	PostHandler    *PostHandler
	WishHandler    *WishHandler
	CommentHandler *CommentHandler
}

// NewHandler creates a new instance of Handler with all required handlers.
func NewHandler(service *services.Service) *Handler {
	return &Handler{
		UserHandler:    NewUserHandler(service.UserService),
		PostHandler:    NewPostHandler(service.PostService),
		WishHandler:    NewWishHandler(service.WishService),
		CommentHandler: NewCommentHandler(service.CommnetService),
	}
}
