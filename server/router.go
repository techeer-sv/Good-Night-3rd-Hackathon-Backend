package server

import (
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/handlers"
	"github.com/gin-gonic/gin"
)

// 라우터 설정
func setupRouter(handler *handlers.Handler) *gin.Engine {
	router := gin.Default()
	apiGroup := router.Group("/api/v1")
	{
		// User routes
		userGroup := apiGroup.Group("/users")
		{
			userGroup.GET("/:id", handler.UserHandler.GetUser)
			userGroup.POST("/", handler.UserHandler.CreateUser)
		}

		// Post routes
		postGroup := apiGroup.Group("/posts")
		{
			postGroup.GET("/:id", handler.PostHandler.GetPost)
			postGroup.POST("/", handler.PostHandler.CreatePost)
		}
	}
	return router
}
