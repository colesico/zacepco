FROM nginx:alpine-slim

# Remove defalut config
RUN rm -f /etc/nginx/conf.d/default.conf

# Create custom config
COPY src/nginx-prod.conf /etc/nginx/conf.d/default.conf

# Crete resources
COPY tmp-resources/ /usr/share/nginx/html/resources/

# Check confs
RUN nginx -t

EXPOSE 80 443
