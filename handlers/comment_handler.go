package handlers

import (
	"net/http"
	"strconv"

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

func (h *CommentHandler) GetComments(c *gin.Context) {
	wishIDStr := c.Query("wish_id")
	pageStr := c.Query("page")
	pageSizeStr := c.Query("page_size")

	wishID, err := strconv.Atoi(wishIDStr)
	if err != nil || wishID <= 0 {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid wish_id"})
		return
	}

	page, err := strconv.Atoi(pageStr)
	if err != nil || page <= 0 {
		page = 1
	}

	pageSize, err := strconv.Atoi(pageSizeStr)
	if err != nil || pageSize <= 0 {
		pageSize = 10
	}

	comments, err := h.service.GetCommentsByWishID(uint(wishID), page, pageSize)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, comments)
}

func (h *CommentHandler) DeleteComment(c *gin.Context) {
	idStr := c.Param("id")
	id, err := strconv.Atoi(idStr)
	if err != nil || id <= 0 {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid comment ID"})
		return
	}

	err = h.service.DeleteComment(uint(id))
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusNoContent, nil)
}