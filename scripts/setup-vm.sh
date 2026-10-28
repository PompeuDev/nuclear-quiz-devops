#!/bin/bash
# roda dentro da VM, instala o docker e sobe o container da api
set -e
cd "$(dirname "$0")/.."

echo ">>> Instalando Docker..."
export DEBIAN_FRONTEND=noninteractive
apt-get update -y
apt-get install -y docker.io
systemctl enable --now docker
docker --version

echo ">>> Gerando a imagem Docker (build Maven dentro do Docker, leva alguns minutos)..."
docker build -t nuclear-quiz-api .

echo ">>> Subindo o container (porta 80 da VM -> 8080 do container)..."
docker rm -f nuclear-quiz-api 2>/dev/null || true
docker run -d --name nuclear-quiz-api -p 80:8080 nuclear-quiz-api

echo ">>> Aguardando a API iniciar..."
for i in $(seq 1 40); do
  if curl -s -o /dev/null http://localhost/api/perguntas; then echo ">>> API respondendo!"; break; fi
  sleep 3
done

echo ">>> Container em execucao:"
docker ps --filter name=nuclear-quiz-api
