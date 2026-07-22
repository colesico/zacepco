docker run -d \
  --name postgres-light \
  -e POSTGRES_PASSWORD=mysecretpassword \
  -e POSTGRES_USER=admin \
  -e POSTGRES_DB=mydb \
  -p 5432:5432 \
  --memory="512m" \
  --cpus="1.0" \
  postgres:16-alpine