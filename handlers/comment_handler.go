package handlers

import (
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/services"
)

type CommentHandler struct {
	commentService    services.CommentService
	wishService services.WishService
}

func NewCommentHandler(commentService services.CommentService, wishService services.WishService) *CommentHandler {
	return &CommentHandler{commentService: commentService, wishService: wishService}
}

func (h *CommentHandler) CreateComment(c *gin.Context) {
	var commentInput models.CommentInput

	if err := c.ShouldBindJSON(&commentInput); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid request payload", "details": err.Error()})
		return
	}

	// 소원 존재 여부 확인
	if exists, err := h.wishService.WishExists(commentInput.WishID); err != nil {
    // 데이터베이스 조회 중 에러가 발생한 경우
    c.JSON(http.StatusInternalServerError, gin.H{"error": "Internal server error"})
    return
} else if !exists {
    // 소원이 존재하지 않는 경우
    c.JSON(http.StatusNotFound, gin.H{"error": "Wish not found"})
    return
}

	newComment := &models.Comment{
		WishID:  commentInput.WishID,
		Content: commentInput.Content,
	}

	if err := h.commentService.CreateComment(newComment); err != nil {
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

	comments, err := h.commentService.GetCommentsByWishID(uint(wishID), page, pageSize)
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

	err = h.commentService.DeleteComment(uint(id))
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusNoContent, nil)
}