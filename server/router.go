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

		wishGroup := apiGroup.Group("/wishes")
		{
			wishGroup.POST("/", handler.WishHandler.RegisterWish)            // 소원 등록
			wishGroup.GET("/:id", handler.WishHandler.GetWish)               // 소원 단일 조회
			wishGroup.GET("/", handler.WishHandler.GetAllWishes)             // 소원 목록 조회
			wishGroup.GET("/pending", handler.WishHandler.GetPendingWishes)  // 보류된 소원 조회
			wishGroup.PATCH("/:id/approve", handler.WishHandler.ApproveWish) // 소원 승인
			wishGroup.PATCH("/:id/reject", handler.WishHandler.RejectWish)   // 소원 거절
			wishGroup.DELETE("/:id", handler.WishHandler.DeleteWish)         // 소원 삭제
		}

		commentGroup := apiGroup.Group("/comments")
		{
			commentGroup.POST("/", handler.CommentHandler.CreateComment)                  // 댓글 등록
			commentGroup.GET("/:id", handler.CommentHandler.GetComment)                   // 댓글 단일 조회
			commentGroup.GET("/wish/:wishID", handler.CommentHandler.GetCommentsByWishID) // 특정 소원의 댓글 조회
			commentGroup.DELETE("/:id", handler.CommentHandler.DeleteComment)             // 댓글 삭제
		}
	}
	return router
}
