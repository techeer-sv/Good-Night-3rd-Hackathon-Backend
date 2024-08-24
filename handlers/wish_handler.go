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