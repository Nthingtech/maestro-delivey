#podman build --ulimit nofile=10240:10240 -f pizza-api/src/main/docker/Dockerfile --no-cache --progress=plain -t docker.io/tux83/pizza-api:latest .
#podman run pizza-api:latest
#podman run tux83/pizza-api:latest
#podman login docker.io
#podman push docker.io/tux83/pizza-api:latest

FROM fedora:43 as build

USER root
RUN bash -c "dnf install -y zip unzip curl"

ARG USERNAME=somebody
ARG USER_UID=1000
ARG USER_GID=$USER_UID
RUN groupadd --gid $USER_GID $USERNAME
RUN useradd --uid $USER_UID --gid $USER_GID -m $USERNAME -d "/home/$USERNAME"

USER $USERNAME
ARG JAVA_SDK="21.0.2-graal"
RUN curl -s "https://get.sdkman.io" | bash
RUN bash -c ". $HOME/.sdkman/bin/sdkman-init.sh \
    && sdk install java $JAVA_SDK \
    && sdk default java $JAVA_SDK \
    && sdk install quarkus \
    && sdk install maven \
    "

RUN mkdir "/home/$USERNAME/quarkus-app"
WORKDIR "/home/$USERNAME/quarkus-app"
COPY --chown=$USERNAME . .

RUN bash -c ". $HOME/.sdkman/bin/sdkman-init.sh \
    && mvn clean install -DskipTests"


FROM fedora:43

USER root

ARG USERNAME=somebody
ARG USER_UID=1000
ARG USER_GID=$USER_UID
RUN groupadd --gid $USER_GID $USERNAME
RUN useradd --uid $USER_UID --gid $USER_GID -m $USERNAME -d "/home/$USERNAME"

ARG GRAALVM_URL="https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-21.0.2/graalvm-community-jdk-21.0.2_linux-x64_bin.tar.gz"
RUN curl -L $GRAALVM_URL -o /tmp/graalvm.tar.gz && \
    tar -xzf /tmp/graalvm.tar.gz -C /usr/local/ && \
    rm /tmp/graalvm.tar.gz && \
    ln -s /usr/local/graalvm-community-openjdk-21.0.2+13.1 /usr/local/graalvm && \
    dnf clean all

USER $USERNAME

# CORREÇÃO 1: Usa o link simbólico criado acima
ENV JAVA_HOME=/usr/local/graalvm
ENV PATH="$JAVA_HOME/bin:$PATH"

# CORREÇÃO 2: Define WORKDIR antes do COPY
WORKDIR /home/$USERNAME/app

# CORREÇÃO 3: Copia do build para o WORKDIR atual
COPY --chown=$USERNAME:$USERNAME --from=build /home/$USERNAME/quarkus-app/pizza-api/target/quarkus-app/ ./

EXPOSE 8080

# CORREÇÃO 4: Usa path relativo ao WORKDIR
ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]