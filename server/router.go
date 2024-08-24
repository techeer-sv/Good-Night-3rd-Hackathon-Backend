package server

import (
	"github.com/gin-gonic/gin"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/handlers"
)

func setupRouter(handler *handlers.Handler) *gin.Engine {
	router := gin.Default()

	apiGroup := router.Group("/api/v1")
	{
		// Wish
		wishGroup := apiGroup.Group("/wishes")
		{
			wishGroup.POST("/", handler.WishHandler.CreateWish)
			wishGroup.DELETE("/:id", handler.WishHandler.DeleteWish)
			wishGroup.PUT("/status", handler.WishHandler.UpdateWisheList)
			wishGroup.PUT("/:id/status", handler.WishHandler.UpdateWish)
		}
	}
	return router
}
