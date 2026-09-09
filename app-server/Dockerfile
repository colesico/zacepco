# =================== #
# Zacepco app image   #
# =================== #

FROM bellsoft/liberica-openjre-alpine:25

EXPOSE 8080

# Build arguments for host-machine paths (ONLY for file copying during build)
ARG APP_BUILD_DIR=modules/main/target
ARG APP_JAR=zacepco-app.jar

# App working directory configuration (MUST be ENV for runtime execution)
ENV WORK_DIR=/opt/app
ENV CONFIG_DIR=${WORK_DIR}/config
ENV STORAGE_DIR=${WORK_DIR}/storage

# Locale and timezone configuration
ENV LANG=ru_RU.UTF-8 \
    LANGUAGE=ru_RU:ru \
    LC_ALL=ru_RU.UTF-8 \
    TZ=UTC

# JVM memory configuration
ENV JVM_OPTIONS="-Xmx280m -Xms128m"

# JFR configuration
ENV JFR_DIR=/var/lib/zacepco/jfr/main
ENV JFR_OPTIONS="-XX:StartFlightRecording=settings=profile.jfc,filename=${JFR_DIR}/dump.jfr,delay=10s,disk=true,maxage=3d,maxsize=30M,dumponexit=true -XX:FlightRecorderOptions=maxchunksize=10m,stackdepth=512"

# Create necessary directories under root user
RUN mkdir -p "${CONFIG_DIR}" && \
    mkdir -p "${JFR_DIR}" && \
    mkdir -p "${STORAGE_DIR}"

WORKDIR ${WORK_DIR}

# Copy application artifacts from host machine using ARG variables
COPY ${APP_BUILD_DIR}/${APP_JAR} ./${APP_JAR}
COPY ${APP_BUILD_DIR}/lib ./lib

# Security: change ownership to unprivileged user and switch context
RUN chown -R nobody:nogroup "${WORK_DIR}" "${JFR_DIR}" "${STORAGE_DIR}"
USER nobody

# Declare external persistent volumes (using direct absolute paths)
VOLUME ["/var/lib/zacepco/jfr/main", "/opt/app/storage"]

# Canonical ENTRYPOINT form using exec to preserve PID 1 for correct OS signal handling
ENTRYPOINT ["sh", "-c", "exec java ${JVM_OPTIONS} ${JFR_OPTIONS} -Dfile.encoding=UTF-8 -jar ${APP_JAR}"]