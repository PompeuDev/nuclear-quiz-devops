#!/bin/bash
# testa a api pelo IP publico da VM
IP="$1"
if [ -z "$IP" ]; then echo "Uso: $0 <IP_PUBLICO_DA_VM>"; exit 1; fi

echo "--- GET /api/perguntas (primeiras linhas)"
curl -s "http://$IP/api/perguntas" | head -c 400; echo " ..."
echo "--- GET /api/perguntas?tema=MEDICINA (primeiras linhas)"
curl -s "http://$IP/api/perguntas?tema=MEDICINA" | head -c 400; echo " ..."
echo "--- GET /api/competicoes/em-andamento (primeiras linhas)"
curl -s "http://$IP/api/competicoes/em-andamento" | head -c 400; echo " ..."
echo "--- POST /api/perguntas (cria uma pergunta)"
curl -s -X POST "http://$IP/api/perguntas" -H "Content-Type: application/json" -d '{
  "enunciado": "Qual e o papel da agua em uma usina nuclear do tipo PWR?",
  "alternativaA": "Resfriar o reator e transportar o calor para gerar vapor",
  "alternativaB": "Servir de combustivel para a fissao",
  "alternativaC": "Produzir radiacao para os exames medicos",
  "alternativaD": "Armazenar a eletricidade gerada",
  "respostaCorreta": "A",
  "explicacao": "A agua retira o calor da fissao e ajuda a produzir o vapor que move as turbinas.",
  "tema": "GERACAO_DE_ENERGIA"
}'; echo ""
echo "--- GET /api/perguntas/9999 (deve retornar 404)"
curl -s -o /dev/null -w "HTTP %{http_code}\n" "http://$IP/api/perguntas/9999"
echo ""
echo "Swagger: http://$IP/swagger-ui/index.html"
