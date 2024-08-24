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

func (h *WishHandler) DeleteWish(c *gin.Context) {
	id, _ := strconv.Atoi(c.Param("id"))
	err := h.service.DeleteWish(uint(id))
	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": "Wish not found"})
		return
	}
	c.JSON(http.StatusOK, gin.H{"message": "Wish deleted successfully"})
}