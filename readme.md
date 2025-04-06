
# 🌤️ Weather Metrics REST Consumer Service

---

- Produces Weather Metric data sensorid, metrics("temperature","humidity"),timestamp
- Looking for a consumer? https://github.com/Droidverine/SpringBootRestSample  
---

## 📦 Requirements

- Java 17
- Maven 3.6+
- Docker & Docker Compose

---

## 🚀 Setup and Run 🏃


### Start Kafka & ZooKeeper 
```
sudo docker compose up -d
```

### Run the app 🏃
```
cd Weatherdata
java -jar target/Weatherdata-0.0.1-SNAPSHOT.jar 
```

### to check topics all the topics on kafka
```
sudo docker exec -it kafka kafka-topics --bootstrap-server localhost:9092 --list
```

### To check topics all the topics on kafka
```
sudo docker exec -it kafka kafka-topics --bootstrap-server localhost:9092 --list
```
### To check messages in the topic
```
sudo docker exec -it kafka kafka-console-consumer   --bootstrap-server localhost:9092   --topic weather-metrics   --from-beginning
```
