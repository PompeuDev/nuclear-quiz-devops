#!/bin/bash
# apaga o grupo de recursos no final
RG="rg-nuclear-quiz"

echo "Recursos existentes em $RG:"
az resource list -g "$RG" -o table
echo ""

echo "Apagando o grupo de recursos $RG..."
az group delete --name "$RG" --yes
echo ""

echo "Comprovando a exclusao (deve retornar 'false'):"
az group exists --name "$RG"
echo ""
echo "Grupos de recursos restantes:"
az group list -o table
