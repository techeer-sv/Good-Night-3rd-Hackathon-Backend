package handlers

import (
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/models"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/services"
)

type WishHandler struct {
	service services.WishService
}

func NewWishHandler(service services.WishService) *WishHandler {
	return &WishHandler{service: service}
}

// @Summary 소원 등록
// @Description 제목, 내용, 카테고리를 입력하여 소원을 등록해보세요.
// @Tags Wishes
// @Accept json
// @Produce json
// @Param wish body models.WishInput true "카테고리의 종류는 진로, 건강, 인간 관계, 돈, 목표, 학업/성적, 기타 총 7가지입니다."
// @Router /api/v1/wishes/ [post]
func (h *WishHandler) CreateWish(c *gin.Context) {
	var wishInput models.WishInput

	if err := c.ShouldBindJSON(&wishInput); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid request payload", "details": err.Error()})
		return
	}

	category := models.Category(wishInput.Category)

	validCategories := map[models.Category]bool{
		models.Career:        true,
		models.Health:        true,
		models.Relationships: true,
		models.Money:         true,
		models.Goals:         true,
		models.Academics:     true,
		models.Other:         true,
	}

	if !validCategories[category] {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid category"})
		return
	}

	newWish := &models.Wish{
		Title:    wishInput.Title,
		Content:  wishInput.Content,
		Category: category,
		IsConfirm: models.Pending,
	}

	if err := h.service.CreateWish(newWish); err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, newWish)
}

// @Summary 소원 삭제
// @Description ID를 입력하여 해당 소원을 삭제할 수 있습니다.
// @Tags Wishes
// @Accept json
// @Produce json
// @Param id path int true "Wish ID"
// @Router /api/v1/wishes/{id} [delete]
func (h *WishHandler) DeleteWish(c *gin.Context) {
	id, _ := strconv.Atoi(c.Param("id"))
	err := h.service.DeleteWish(uint(id))
	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": "Wish not found"})
		return
	}
	c.JSON(http.StatusOK, gin.H{"message": "Wish deleted successfully"})
}

// @Summary 모든 소원 상태 변경
// @Description 모든 소원을 승인 또는 거절 상태로 변경할 수 있습니다.
// @Tags Wishes
// @Accept json
// @Produce json
// @Param status query string true "승인: approved, 거절: rejected"
// @Router /api/v1/wishes/status [put]
func (h *WishHandler) UpdateWisheList(c *gin.Context) {
	status := c.Query("status")
	if status != string(models.Approved) && status != string(models.Rejected) {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid status"})
		return
	}

	err := h.service.UpdateAllWishes(status)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "Failed to update all wishes"})
		return
	}

	c.JSON(http.StatusOK, gin.H{"message": "All wishes status updated successfully"})
}

// @Summary 개별 소원 상태 변경
// @Description ID를 입력하여 해당 소원의 상태를 변경할 수 있습니다.
// @Tags Wishes
// @Accept json
// @Produce json
// @Param id path int true "Wish ID"
// @Param status query string true "승인: approved, 거절: rejected"
// @Router /api/v1/wishes/{id}/status [put]
func (h *WishHandler) UpdateWish(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid ID"})
		return
	}

	status := c.Query("status")
	if status != string(models.Approved) && status != string(models.Rejected) {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid status"})
		return
	}

	err = h.service.UpdateWish(uint(id), status)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "Failed to update wish status"})
		return
	}

	c.JSON(http.StatusOK, gin.H{"message": "Wish status updated successfully"})
}

// @Summary 개별 소원 조회
// @Description ID를 입력하여 승인된 소원을 조회할 수 있습니다.
// @Tags Wishes
// @Accept json
// @Produce json
// @Param id path int true "Wish ID"
// @Router /api/v1/wishes/{id} [get]
func (h *WishHandler) GetWish(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid ID"})
		return
	}

	wish, err := h.service.GetWishByID(uint(id))
	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": "Wish not found or not accessible"})
		return
	}

	response := gin.H{
		"title":    wish.Title,
		"content":  wish.Content,
		"category": wish.Category,
	}

	c.JSON(http.StatusOK, response)
}

// @Summary 소원 목록 조회
// @Description 조회하려는 소원의 상태와 페이지(번호, 크기)를 선택할 수 있습니다.
// @Tags Wishes
// @Accept json
// @Produce json
// @Param status query string false "승인: approved, 보류: pending, 거절: rejected"
// @Param page query int false "페이지 번호" default(1)
// @Param page_size query int false "페이지 크기" default(10)
// @Router /api/v1/wishes [get]
func (h *WishHandler) GetWishList(c *gin.Context) {
	status := c.Query("status")
	page, _ := strconv.Atoi(c.DefaultQuery("page", "1"))
	pageSize, _ := strconv.Atoi(c.DefaultQuery("page_size", "10"))

	wishes, err := h.service.GetAllWishes(status, page, pageSize)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "Failed to retrieve wishes"})
		return
	}

	var response []gin.H
	for _, wish := range wishes {
		response = append(response, gin.H{
			"title":      wish.Title,
			"category":   wish.Category,
			"created_at": wish.CreatedAt,
		})
	}

	c.JSON(http.StatusOK, response)
}