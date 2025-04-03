to run kafka & zookeeper in container
docker compose up -d




to check topic if there
sudo docker exec -it kafka kafka-topics --bootstrap-server localhost:9092 --list

to check messages in the topic
sudo docker exec -it kafka kafka-console-consumer   --bootstrap-server localhost:9092   --topic weather-metrics   --from-beginning



want service to start automatically? add following to docker-compose.yml:
  weather-producer:
    image: maven:3.8.8-eclipse-temurin-17
    container_name: weather-producer
    depends_on:
      - kafka
    volumes:
      - ./weather-servic/Weatherdata:/app
    working_dir: /app
    command: /bin/sh -c "mvn clean package && java -jar target/weather-producer-1.0.0.jar"
