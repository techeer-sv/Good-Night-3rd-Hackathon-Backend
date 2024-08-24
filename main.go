package main

import (
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/config"
	"github.com/hoon99/Good-Night-3rd-Hackathon-Backend/server"
)

func main() {
	port := config.GetEnvVarAsString("PORT", "8080")
	server.Start(port)
}
