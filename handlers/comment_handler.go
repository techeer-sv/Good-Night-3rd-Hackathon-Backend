package handlers

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/services"
)

type CommentHandler struct {
	service    services.CommentService
	wishService services.WishService
}

func NewCommentHandler(service services.CommentService, wishService services.WishService) *CommentHandler {
	return &CommentHandler{service: service, wishService: wishService}
}

func (h *CommentHandler) CreateComment(c *gin.Context) {
	var commentInput models.CommentInput

	if err := c.ShouldBindJSON(&commentInput); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid request payload", "details": err.Error()})
		return
	}

	// Check if the wish exists
	if exists, err := h.wishService.WishExists(commentInput.WishID); err != nil || !exists {
		c.JSON(http.StatusNotFound, gin.H{"error": "Wish not found"})
		return
	}

	newComment := &models.Comment{
		WishID:  commentInput.WishID,
		Content: commentInput.Content,
	}

	if err := h.service.CreateComment(newComment); err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusCreated, newComment)
}