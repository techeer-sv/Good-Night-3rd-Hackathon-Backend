package main

import (
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/config"
	"github.com/d0kyoung/Techeer-Good-Night-3rd-Hackathon-Backend/server"
)

// 앱에서 가장 먼저 실행되는 함수
func main() {
	port := config.GetEnvVarAsString("PORT", "8080")
	server.Start(port)
}
