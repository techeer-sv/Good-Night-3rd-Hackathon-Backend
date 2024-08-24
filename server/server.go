package server

import (
	"fmt"

	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/database"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/handlers"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/repositories"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/services"
)

func Start(port string) {
	db := database.SetupDatabase()
	repo := repositories.NewRepository(db)
	service := services.NewService(repo)
	handler := handlers.NewHandler(service)

	router := setupRouter(handler)
	router.Run(fmt.Sprintf(":%s", port))
}
