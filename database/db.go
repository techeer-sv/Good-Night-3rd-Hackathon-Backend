package database

import (
	"fmt"
	"log"

	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/config"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/models"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func SetupDatabase() *gorm.DB {
	config.LoadEnvFile(".env")
	dsn := fmt.Sprintf(
		"host=%s user=%s password=%s dbname=%s port=%s sslmode=%s",
		config.GetEnvVarAsString("DB_HOST", "localhost"),
		config.GetEnvVarAsString("DB_USER", "techeer"),
		config.GetEnvVarAsString("DB_PASSWORD", "secret"),
		config.GetEnvVarAsString("DB_NAME", "hackathon"),
		config.GetEnvVarAsString("DB_PORT", "5432"),
		config.GetEnvVarAsString("DB_SSLMODE", "disable"),
	)
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatal("Failed to connect to database: ", err)
	}
	db.AutoMigrate(&models.User{})
	db.AutoMigrate(&models.Post{})

	return db
}
