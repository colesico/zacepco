FROM nginx:alpine-slim

# Remove defalut config
RUN rm -f /etc/nginx/conf.d/default.conf

COPY ./conf.d/ /etc/nginx/

# Check confs
RUN nginx -t

EXPOSE 80 443
