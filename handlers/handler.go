package handlers

import "github.com/hoon99/Good-Night-3rd-Hackathon-Backend/services"

// Handler groups all individual handlers.
type Handler struct {
	WishHandler *WishHandler
	CommentHandler *CommentHandler
}

// NewHandler creates a new instance of Handler with all required handlers.
func NewHandler(service *services.Service) *Handler {
	return &Handler{
		WishHandler: NewWishHandler(service.WishService),
		CommentHandler: NewCommentHandler(service.CommentService, service.WishService),
	}
}
