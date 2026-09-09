@echo off

docker volume create zacepco-pgdata

docker rm -f zacepco-postgres

docker run -d ^
  --name zacepco-postgres ^
  -e POSTGRES_PASSWORD=postgres ^
  -e POSTGRES_USER=postgres ^
  -e POSTGRES_DB=zacepco ^
  -v zacepco-pgdata:/var/lib/postgresql/data ^
  -p 5432:5432 ^
  --memory="512m" ^
  --cpus="1.0" ^
  postgres:16-alpine
pause