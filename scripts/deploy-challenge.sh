#!/bin/bash
# cria o grupo de recursos e a VM no Azure pelo CLI, o docker eu configuro depois por ssh

RESOURCE_GROUP="rg-nuclear-quiz"
LOCATION="spaincentral"
VM_NAME="vm-nuclear-quiz"
IMAGE="Ubuntu2404"
VM_SIZE="Standard_B2ls_v2"
ADMIN_USERNAME="admlnx"
ADMIN_PASSWORD="${ADMIN_PASSWORD:-}"
VNET_NAME="vnet-nuclear-quiz"
NSG_NAME="nsg-nuclear-quiz"
PUBLIC_IP_NAME="pip-nuclear-quiz"
PORT_HTTP=80
TAG_OWNER="NucleAR-Quiz"
TAG_PURPOSE="Training"

if [ -z "$ADMIN_PASSWORD" ]; then
  read -r -s -p "Defina a senha do usuario $ADMIN_USERNAME da VM (min. 12 caracteres, maiuscula, numero e simbolo): " ADMIN_PASSWORD
  echo ""
fi

echo "*************************************"
echo "  Challenge DevOps - Criacao da VM no Azure via Azure CLI"
echo "*************************************"
echo ""

echo "Versao do Azure CLI e assinatura atual:"
az --version | head -n 1
az account show --query "{Sub:name, Id:id}" -o jsonc
echo ""

echo "Criando grupo de recursos '$RESOURCE_GROUP' na localizacao '$LOCATION'..."
az group create --name "$RESOURCE_GROUP" --location "$LOCATION"
echo ""

echo "Informacoes do grupo de recursos criado:"
az group show -n "$RESOURCE_GROUP" --query "{Name:name, Location:location, Tags:tags}" --output table
echo ""

echo "Criando VM '$VM_NAME' no grupo de recursos '$RESOURCE_GROUP' (pode levar alguns minutos)..."
az vm create \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  --image "$IMAGE" \
  --size "$VM_SIZE" \
  --authentication-type password \
  --admin-username "$ADMIN_USERNAME" \
  --admin-password "$ADMIN_PASSWORD" \
  --vnet-name "$VNET_NAME" \
  --nsg "$NSG_NAME" \
  --public-ip-address "$PUBLIC_IP_NAME" \
  --public-ip-sku Standard \
  --tags "owner=$TAG_OWNER" "purpose=$TAG_PURPOSE"
if [ $? -ne 0 ]; then
  echo "ERRO: a VM nao foi criada. Leia a mensagem de erro acima."
  exit 1
fi
echo ""

echo "Abrindo a porta $PORT_HTTP para trafego na VM '$VM_NAME'..."
az vm open-port --resource-group "$RESOURCE_GROUP" --name "$VM_NAME" --port "$PORT_HTTP" --priority 1001
echo ""

echo "Recursos criados no grupo '$RESOURCE_GROUP':"
az resource list -g "$RESOURCE_GROUP" -o table
echo ""
IP=$(az vm show -d -g "$RESOURCE_GROUP" -n "$VM_NAME" --query publicIps -o tsv)
echo "IP publico da VM: $IP"
echo ""
echo "Proximo passo - conectar na VM por SSH e configurar o Docker:"
echo "   ssh $ADMIN_USERNAME@$IP"
echo "   (dentro da VM, siga os comandos do README ou rode: sudo ./scripts/setup-vm.sh)"
echo ""
echo "Script finalizado com sucesso!"
