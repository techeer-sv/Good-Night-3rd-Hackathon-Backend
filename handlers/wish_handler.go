package handlers

import (
	"net/http"
	"strconv"

	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/models"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/services"
	"github.com/gin-gonic/gin"
)

type WishHandler struct {
	service services.WishService
}

func NewWishHandler(service services.WishService) *WishHandler {
	return &WishHandler{service: service}
}

func (h *WishHandler) RegisterWish(c *gin.Context) {
	var wish models.Wish
	if err := c.ShouldBindJSON(&wish); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid input"})
		return
	}

	if err := h.service.RegisterWish(&wish); err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusCreated, wish)
}

func (h *WishHandler) DeleteWish(c *gin.Context) {
	id, _ := strconv.ParseUint(c.Param("id"), 10, 32)
	if err := h.service.DeleteWish(uint(id)); err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"message": "Wish deleted successfully"})
}

func (h *WishHandler) GetPendingWishes(c *gin.Context) {
	page, _ := strconv.Atoi(c.DefaultQuery("page", "1"))
	pageSize, _ := strconv.Atoi(c.DefaultQuery("pageSize", "10"))

	wishes, totalCount, err := h.service.GetAllWishes("보류됨", page, pageSize)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	response := gin.H{
		"totalCount": totalCount,
		"wishes":     wishes,
	}

	c.JSON(http.StatusOK, response)
}

func (h *WishHandler) ApproveWish(c *gin.Context) {
	id, _ := strconv.ParseUint(c.Param("id"), 10, 32)
	if err := h.service.ApproveWish(uint(id)); err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"message": "Wish approved successfully"})
}

func (h *WishHandler) RejectWish(c *gin.Context) {
	id, _ := strconv.ParseUint(c.Param("id"), 10, 32)
	if err := h.service.RejectWish(uint(id)); err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"message": "Wish rejected successfully"})
}

func (h *WishHandler) GetWish(c *gin.Context) {
	id, _ := strconv.ParseUint(c.Param("id"), 10, 32)
	wish, err := h.service.GetWish(uint(id))
	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": "Wish not found or not approved"})
		return
	}

	c.JSON(http.StatusOK, wish)
}

func (h *WishHandler) GetAllWishes(c *gin.Context) {
	status := c.Query("status")
	page, _ := strconv.Atoi(c.DefaultQuery("page", "1"))
	pageSize, _ := strconv.Atoi(c.DefaultQuery("pageSize", "10"))

	wishes, totalCount, err := h.service.GetAllWishes(status, page, pageSize)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	response := gin.H{
		"totalCount": totalCount,
		"wishes":     wishes,
	}

	c.JSON(http.StatusOK, response)
}
