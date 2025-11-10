# InvestigacionSOA-Herrera-Sierra-Vargas
# 🚲 Bike Store Async
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.9-blue)
![Docker](https://img.shields.io/badge/Docker-Desktop-lightgrey)

Aplicación de ejemplo en **Spring Boot** que simula un sistema de pedidos asíncronos usando **RabbitMQ**.  
Permite enviar pedidos, procesar pagos y enviar notificaciones por correo de manera **asíncrona**.

---

## 🛠 Tecnologías

- Java 17 / 21
- Spring Boot
- Spring AMQP (RabbitMQ)
- Maven
- Docker (para levantar RabbitMQ)

---

## ⚙️ Funcionalidades

- 📦 Enviar pedidos vía API REST (`/orders/create`)
- 💳 Procesar pagos de manera aleatoria (simula éxito/fallo)
- 🔄 Reintentos automáticos en caso de fallo
- 📨 Enviar notificación por correo (simulada en logs)
- ⚠️ Manejo de Dead Letter Queue (DLQ) para pedidos fallidos
- 📝 Conversión automática de objetos Java a JSON usando `Jackson2JsonMessageConverter`

---

## 🚀 Instalación y ejecución

### 1️⃣ Clonar repositorio
bash
git clone https://github.com/tu-usuario/bike-store-async.git
cd bike-store-async
2️⃣ Levantar RabbitMQ con Docker
bash
Copiar código
docker run -d --name rabbitmq \
  -p 5672:5672 -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=user \
  -e RABBITMQ_DEFAULT_PASS=password \
  rabbitmq:3-management
Panel web de RabbitMQ: http://localhost:15672
Usuario: user | Contraseña: password

3️⃣ Ejecutar la aplicación
IntelliJ: abrir BikeStoreAsyncApplication.java → botón Run

O desde terminal:

bash
Copiar código
mvn spring-boot:run
4️⃣ Probar la API
POST a http://localhost:8080/orders/create con body (JSON):

json
Copiar código
{
  "customerEmail": "cliente@ejemplo.com",
  "amount": 100.0
}
Logs esperados en consola:

php-template
Copiar código
📦 Enviando pedido: <ID>
💳 Procesando pago del pedido: <ID>
✅ Pago aprobado para el pedido <ID>
📨 Enviando correo a <cliente@ejemplo.com> por el pedido <ID>
Revisar colas en RabbitMQ:

order.payment.queue

order.email.queue

order.dead.queue
