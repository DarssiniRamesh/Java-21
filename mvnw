#!/bin/sh
# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup script for UN*X
# ----------------------------------------------------------------------------

# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

# ----------------------------------------------------------------------------
# Maven Start Up Batch script
#
# Required ENV vars:
# ------------------
#   JAVA_HOME - location of a JDK home dir
#
# Optional ENV vars
# -----------------
#   MAVEN_OPTS - parameters passed to the Java VM when running Maven
#     e.g. to debug Maven itself, use
#       set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
#   MAVEN_SKIP_RC - flag to disable loading of mavenrc files
# ----------------------------------------------------------------------------

if [ -z "$MAVEN_SKIP_RC" ] ; then

  if [ -f /usr/local/etc/mavenrc ] ; then
    . /usr/local/etc/mavenrc
  fi

  if [ -f /etc/mavenrc ] ; then
    . /etc/mavenrc
  fi

  if [ -f "$HOME/.mavenrc" ] ; then
    . "$HOME/.mavenrc"
  fi

fi

# OS specific support.
cygwin=false;
darwin=false;
mingw=false
case "`uname`" in
  CYGWIN*) cygwin=true ;;
  MINGW*) mingw=true;;
  Darwin*) darwin=true
    # Use /usr/libexec/java_home if available, otherwise fall back to /Library/Java/Home
    # See https://developer.apple.com/library/mac/qa/qa1170/_index.html
    if [ -z "$JAVA_HOME" ]; then
      if [ -x "/usr/libexec/java_home" ]; then
        JAVA_HOME="`/usr/libexec/java_home`"; export JAVA_HOME
      else
        JAVA_HOME="/Library/Java/Home"; export JAVA_HOME
      fi
    fi
    ;;
esac

# For Cygwin or MinGW, ensure paths are in UNIX format before anything is touched
if $cygwin ; then
  [ -n "$JAVA_HOME" ] && JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
fi
if $mingw ; then
  [ -n "$JAVA_HOME" ] && JAVA_HOME="`(cd "$JAVA_HOME"; pwd)`"
fi

# Determine the location of the script
PRG="$0"
# Resolve symlinks
while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`"/$link"
  fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

MAVEN_PROJECTBASEDIR=${MAVEN_BASEDIR:-"$APP_HOME"}

WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

# If the wrapper jar doesn't exist, try to download it from wrapperUrl in properties
if [ ! -f "$WRAPPER_JAR" ]; then
  PROPERTIES_FILE="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"
  if [ -f "$PROPERTIES_FILE" ]; then
    # look for wrapperUrl
    wrapperUrl=`sed -n 's/^wrapperUrl=//p' "$PROPERTIES_FILE"`
    if [ -z "$wrapperUrl" ]; then
      # fallback to default jar location
      wrapperUrl="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
    fi
  else
    wrapperUrl="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
  fi

  mkdir -p "$MAVEN_PROJECTBASEDIR/.mvn/wrapper"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "$wrapperUrl" -o "$WRAPPER_JAR"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "$wrapperUrl" -O "$WRAPPER_JAR"
  else
    echo "Error: Please install 'curl' or 'wget' to download Maven Wrapper." >&2
    exit 1
  fi
fi

# Provide a "standardized" way to retrieve the CLI builder
MAVEN_OPTS="${MAVEN_OPTS:-}"

# For Cygwin, switch paths to Windows format before running java
if $cygwin ; then
  [ -n "$JAVA_HOME" ] && JAVA_HOME=`cygpath --path --mixed "$JAVA_HOME"`
  [ -n "$CLASSPATH" ] && CLASSPATH=`cygpath --path --mixed "$CLASSPATH"`
  [ -n "$MAVEN_PROJECTBASEDIR" ] && MAVEN_PROJECTBASEDIR=`cygpath --path --mixed "$MAVEN_PROJECTBASEDIR"`
fi

# Escape application args
save () {
  for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done
  echo " "
}
APP_ARGS=`save "$@"`

# Collect all JAVA_OPTS and MAVEN_OPTS
JAVA_RUNNER="${JAVA_HOME:+$JAVA_HOME/bin/}java"

# Execute Maven Wrapper
exec $JAVA_RUNNER $MAVEN_OPTS -classpath "$WRAPPER_JAR" "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECTBASEDIR}" $WRAPPER_LAUNCHER "$@"
