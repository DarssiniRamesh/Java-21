#!/bin/sh
# ----------------------------------------------------------------------------
# Maven Wrapper Startup Script for Unix
# ----------------------------------------------------------------------------
# This is a lightweight wrapper script enabling consistent Maven usage without requiring Maven pre-installed.
# It downloads the Maven Wrapper JAR if missing and delegates to org.apache.maven.wrapper.MavenWrapperMain.

# Resolve the directory of this script
PRG="$0"
while [ -h "$PRG" ]; do
  ls=$(ls -ld "$PRG")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=$(dirname "$PRG")/"$link"
  fi
done
PRGDIR=$(dirname "$PRG")
BASEDIR=$(cd "$PRGDIR" >/dev/null 2>&1 && pwd)

MAVEN_PROJECTBASEDIR=${MAVEN_BASEDIR:-"$BASEDIR"}

WRAPPER_DIR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/maven-wrapper.jar"
WRAPPER_PROPERTIES="$WRAPPER_DIR/maven-wrapper.properties"

# Download the maven-wrapper.jar if necessary
if [ ! -f "$WRAPPER_JAR" ]; then
  if [ -f "$WRAPPER_PROPERTIES" ]; then
    DOWNLOAD_URL=$(sed -n 's/^wrapper\.url=//p' "$WRAPPER_PROPERTIES")
  fi
  if [ -z "$DOWNLOAD_URL" ]; then
    DOWNLOAD_URL="https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
  fi
  mkdir -p "$WRAPPER_DIR"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "$DOWNLOAD_URL" -o "$WRAPPER_JAR"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "$DOWNLOAD_URL" -O "$WRAPPER_JAR"
  else
    echo "Error: Please install 'curl' or 'wget' to download Maven Wrapper." >&2
    exit 1
  fi
fi

# Setup JVM options
MAVEN_OPTS="${MAVEN_OPTS:-}"
WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

# Execute Maven Wrapper
exec "${JAVA_HOME:+$JAVA_HOME/bin/}java" $MAVEN_OPTS -classpath "$WRAPPER_JAR" "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECTBASEDIR}" $WRAPPER_LAUNCHER "$@"
