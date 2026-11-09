# chatGptIntegration

API Spring Boot que encaminha um prompt para a API de Chat Completions da OpenAI e devolve a resposta do modelo.

> Projeto de estudo (dezembro/2024), focado em consumir uma API externa com `RestTemplate`/client dedicado e mapear o JSON de request e response em records/POJOs.

## Stack

- Java 21
- Spring Boot 3.4.0 (Web)
- API da OpenAI (`/v1/chat/completions`)

## Estrutura

```
client/OpenAiClient.java      → configuração do client HTTP para a OpenAI
controllers/ChatController.java → expõe o endpoint /chat
services/ChatService.java     → monta o ChatRequest e interpreta o ChatResponse
models/                       → ChatRequest, ChatResponse, Choice, Message,
                                PromptRequest, ResponseMessage
```

O mapeamento acompanha o contrato da OpenAI: `ChatRequest` carrega `model`, `messages` e `temperature`; `ChatResponse` carrega a lista de `choices`, cada uma com uma `Message` (`role` + `content`).

## Configuração

A chave da API é lida de uma variável de ambiente — nada de credencial no repositório:

```properties
openai.api.key=${API_KEY}
```

```bash
export API_KEY="sk-..."      # Linux/macOS
$env:API_KEY = "sk-..."      # PowerShell
```

## Como rodar

```bash
./mvnw spring-boot:run
```

## Endpoint

| Método | Rota    | Descrição                               |
|--------|---------|-----------------------------------------|
| `GET`  | `/chat` | Envia um prompt e devolve a resposta     |

```bash
curl "http://localhost:8080/chat?prompt=Explique%20o%20que%20e%20um%20bean%20no%20Spring"
```

## Limitações

Sem histórico de conversa (cada chamada é isolada), sem streaming e sem tratamento de rate limit ou de erro da API externa.
