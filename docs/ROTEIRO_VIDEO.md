# Roteiro do vídeo (≈3 min) — 720p+, YouTube "Não listado", COM ÁUDIO explicando

Dicas: grave a tela em 1080p com fonte grande no Cloud Shell (maximize a janela). Fale cada passo em voz alta.
A criação da VM e o `docker build` demoram: grave em partes e corte/acelere as esperas na edição, avisando na fala ("o build leva alguns minutos, vou acelerar").

| Tempo | O que mostrar | O que falar |
|---|---|---|
| 0:00–0:20 | README no GitHub com nomes/RMs e o diagrama de arquitetura | Apresentar o grupo, o NucleAR Quiz e a arquitetura (CLI → VM → Docker → container) |
| 0:20–0:40 | Repositório no GitHub (Dockerfile, scripts) e `cat Dockerfile` | Explicar o multi-stage: estágio Maven gera o .jar, estágio JRE executa; usuário não-root; EXPOSE/ENTRYPOINT |
| 0:40–1:15 | Cloud Shell: `./scripts/deploy-challenge.sh` (az group create, az vm create, open-port) | Explicar cada comando Azure CLI; mostrar o IP público ao final |
| 1:15–2:00 | `ssh admlnx@IP` → instalar Docker → `git clone` → `docker build` → `docker run` → `docker ps` / `docker logs` | Mostrar que a imagem foi gerada e o container está **Up dentro da VM** |
| 2:00–2:30 | `./scripts/testar-api.sh IP` e Swagger abrindo em `http://IP/swagger-ui/index.html` | Provar que roda no **IP público da Azure** (NÃO localhost) |
| 2:30–2:45 | Portal Azure: RG, VM, IP público, NSG (ou `az resource list`) | Comprovar que o recurso foi provisionado |
| 2:45–3:00 | `./scripts/cleanup-challenge.sh` → `az group exists` = false | Mostrar a exclusão do grupo de recursos |

## Checklist anti-penalidade
- [ ] Aplicação rodando na VM Azure (não em localhost) — −50 / −100
- [ ] VM criada por Azure CLI — −15
- [ ] Script .sh presente (deploy-challenge.sh) — −15
- [ ] Container da API visível (`docker ps` na VM) — −50
- [ ] Dockerfile no repo — −20
- [ ] Exclusão do RG comprovada — −5
- [ ] Repo GitHub público/acessível ao professor + README — −50
- [ ] Diagrama com ícones/serviços Azure e coerente com a solução — −30
- [ ] Vídeo ≥720p, legível, com áudio, "Não listado" — −10/−20/−100
- [ ] PDF final com Nome, RM, turma, link GitHub e link YouTube — −100
