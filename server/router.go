package server

import (
	"github.com/gin-gonic/gin"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/docs"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/handlers"
	files "github.com/swaggo/files"
	ginSwagger "github.com/swaggo/gin-swagger"
)

func setupRouter(handler *handlers.Handler) *gin.Engine {
	router := gin.Default()

	docs.SwaggerInfo.Title = "Techeer Tree API"
	docs.SwaggerInfo.Description = "Wish List"
	docs.SwaggerInfo.Version = "1.0"
	docs.SwaggerInfo.Host = "localhost:8080"
	docs.SwaggerInfo.BasePath = ""
	docs.SwaggerInfo.Schemes = []string{"http", "https"}

	// Swagger 엔드포인트 설정
	router.GET("/swagger/*any", ginSwagger.WrapHandler(files.Handler))

	apiGroup := router.Group("/api/v1")
	{
		// Wish
		wishGroup := apiGroup.Group("/wishes")
		{
			wishGroup.POST("/", handler.WishHandler.CreateWish)
			wishGroup.DELETE("/:id", handler.WishHandler.DeleteWish)
			wishGroup.PUT("/status", handler.WishHandler.UpdateWisheList)
			wishGroup.PUT("/:id/status", handler.WishHandler.UpdateWish)
			wishGroup.GET("/:id", handler.WishHandler.GetWish)
			wishGroup.GET("/", handler.WishHandler.GetWishList)
		}

		// Comment
		commentGroup := apiGroup.Group("/comments")
		{
			commentGroup.POST("/", handler.CommentHandler.CreateComment)
			commentGroup.GET("/", handler.CommentHandler.GetComments)
			commentGroup.DELETE("/:id", handler.CommentHandler.DeleteComment)
		}
	}
	return router
}
