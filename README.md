# MiniVault API

### Prerequisites  
- Java 21  
- Podman/Docker

### Running

#### Fully automatic
You can run this app in fully automatic mode using "Apache Maven Wrapper", which will download and install "Apache Maven" if it's not already installed on your system. This method will also launch "Ollama" via "Podman"/"Docker" and pull pre-configured model ( see `spring.ai.ollama.chat.options.model` in `src/main/resources/application.properties` ).

Use: 
```
./mvnw spring-boot:run
```

On Windows, use:
```
mvnw.cmd spring-boot:run
```

Once launched, you can access REST API endpoints; see "Testing" section below for `curl` commands as well as "Postman" collection.

#### With Ollama already running
This method is preferable as models are persisted across app restarts, hence app launch is instantaneous.

You can use your existing "Ollama" instance or launch "Ollama" separately via the supplied `docker-compose.yaml`, i.e.: 
```
docker compose up -d 
```

Then, run app via:
```
./mvnw spring-boot:run -Dspring-boot.run.arguments=--arconia.dev.services.ollama.enabled=false
```

On Windows, use:
```
mvnw.cmd spring-boot:run -Dspring-boot.run.arguments=--arconia.dev.services.ollama.enabled=false
```

### Testing

#### Integration tests
To run integration tests, use:
```
./mvnw test
```

On Windows, use:
```
mvnw.cmd test
```

#### CURL
Send request to `/generate` REST API endpoint:
```
curl -X POST \
 -H 'Content-Type: application/json' \
 -H "Accept: application/json" \
 --data '{"prompt":"Hello world!"}' \
  http://localhost:8080/generate
```

Send request to `/generateAsync` REST API endpoint and receive streamed response:
```
curl --no-buffer -X POST \
 -H 'Content-Type: application/json' \
 -H "Accept: application/json" \
 --data '{"prompt":"Hello world!"}' \
  http://localhost:8080/generateAsync
```

#### Postman collection
Postman collection is also included - please see `MiniVault-API.postman_collection.json`.


### Additional information

#### Improvements
I imagine such AI appliance ( product which ["ModelVault"](https://www.modelvault.com/) is building ) as a hub of sorts, which can be both interacted with and connected to from different "dumb" devices which can thus become "intelligent".

Possible functional improvements include:
 - memory - i.e. ability to have "conversations" which span across multiple interactions with the LLM;
 - multi-modality - i.e. ability to interact not only via text input/output, but also via image and audio;
 - RAG / vector database - e.g. ability to interface with network attached storage / external disk containing documents, which once ingested can be queried;
 - tool calling - i.e. ability to connect external APIs providing real-time data as well as apps such as calendar, email, etc.

#### Logging
Log configuration can be found in: 
 - `src/main/resources/application.properties`
 - `src/main/resources/logback-spring.xml`

Currently, it is configured to log both to console AND log model interactions to file.


#### Tech stack
- "Spring Boot": https://spring.io/projects/spring-boot
- "Spring AI": https://spring.io/projects/spring-ai, https://spring.io/ai
- "Ollama": https://ollama.com/
- "Arconia Dev Services": https://arconia.io/docs/arconia/latest/dev-services/, https://arconia.io/docs/arconia/latest/dev-services/ollama/
