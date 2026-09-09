# =================== #
# Zacepco app image   #
# =================== #

# Fixed: Using the exact valid name from Docker Hub without a hyphen
FROM bellsoft/liberica-openjre-alpine:25

EXPOSE 8080

# Build arguments for host-machine paths (ONLY for file copying during build)
ARG APP_BUILD_DIR=modules/main/target
ARG APP_JAR=zacepco-app.jar

# App working directory configuration (MUST be ENV for runtime execution)
ENV WORK_DIR=/opt/app
ENV CONFIG_DIR=${WORK_DIR}/config
ENV STORAGE_DIR=${WORK_DIR}/storage

# Saved the jar name from ARG to ENV so it is available at runtime
ENV RUN_JAR=${APP_JAR}

# Locale and timezone configuration
ENV LANG=ru_RU.UTF-8 \
    LANGUAGE=ru_RU:ru \
    LC_ALL=ru_RU.UTF-8 \
    TZ=UTC

# JVM memory configuration
ENV JVM_OPTIONS="-Xmx280m -Xms128m"

# Create necessary directories under root user
RUN mkdir -p "${CONFIG_DIR}" && \
    mkdir -p "${STORAGE_DIR}"

WORKDIR ${WORK_DIR}

# Copy application artifacts from host machine using ARG variables
COPY ${APP_BUILD_DIR}/${APP_JAR} ./${APP_JAR}
COPY ${APP_BUILD_DIR}/lib ./lib

RUN apk add --no-cache wget

# Security: change ownership to unprivileged user and switch context
RUN chown -R nobody:nogroup "${WORK_DIR}" "${STORAGE_DIR}"
USER nobody

# Declare external persistent volumes (using direct absolute paths)
VOLUME ["/opt/app/storage"]

# Correct shell-form to natively parse ENV variables
ENTRYPOINT exec java ${JVM_OPTIONS} -Dfile.encoding=UTF-8 -jar ${RUN_JAR}