docker run -d \
  --name postgres-light \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_DB=zacepco \
  -v /c/opt/postgres/data:/var/lib/postgresql/data \
  -p 5432:5432 \
  --memory="512m" \
  --cpus="1.0" \
  postgres:16-alpine