ARG IMAGE_BUILD="eclipse-temurin:21.0.3_9-jdk-alpine"
ARG IMAGE_RUN="eclipse-temurin:21.0.3_9-jre-alpine"

FROM ${IMAGE_BUILD} AS builder

COPY . .

RUN ./gradlew clean build -x test --no-daemon


FROM ${IMAGE_RUN} AS runner

ARG APP_NAME="microsserviços"
ARG APP_VERSION="v1.0.0"
ARG APP_DESCRIPTION="Microsserviços em Java (21Lts) e Spring Boot (3.5.0)."

ARG DEVELOPER="juniorsmartins"
ARG MAINTAINER="juniorsmartins"
ARG CONTATO="juniorsoaresmartins@gmail.com"

LABEL aplication=${APP_NAME} \
    version=${APP_VERSION} \
    description=${APP_DESCRIPTION} \
    authors=${DEVELOPER} \
    maintainer=${MAINTAINER} \
    contato=${CONTATO}

RUN apk add --no-cache curl

COPY --from=builder /build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

