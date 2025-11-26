@ECHO OFF
REM ----------------------------------------------------------------------------
REM Apache Maven Wrapper startup script for Windows
REM ----------------------------------------------------------------------------

REM Licensed to the Apache Software Foundation (ASF) under one
REM or more contributor license agreements.  See the NOTICE file
REM distributed with this work for additional information
REM regarding copyright ownership.  The ASF licenses this file
REM to you under the Apache License, Version 2.0 (the
REM "License"); you may not use this file except in compliance
REM with the License.  You may obtain a copy of the License at
REM
REM    https://www.apache.org/licenses/LICENSE-2.0
REM
REM Unless required by applicable law or agreed to in writing,
REM software distributed under the License is distributed on an
REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
REM KIND, either express or implied.  See the License for the
REM specific language governing permissions and limitations
REM under the License.

@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir

@REM Optional ENV vars
@REM MAVEN_OPTS - parameters passed to the Java VM when running Maven

setlocal

set DIR=%~dp0
if "%MAVEN_BASEDIR%"=="" set MAVEN_PROJECTBASEDIR=%DIR%
if not "%MAVEN_BASEDIR%"=="" set MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%

set WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar
set WRAPPER_PROPERTIES=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

IF NOT EXIST "%WRAPPER_JAR%" (
  set "DOWNLOAD_URL="
  IF EXIST "%WRAPPER_PROPERTIES%" (
    FOR /F "usebackq tokens=1,2 delims==" %%A IN ("%WRAPPER_PROPERTIES%") DO (
      IF "%%A"=="wrapperUrl" SET "DOWNLOAD_URL=%%B"
    )
  )
  IF "%DOWNLOAD_URL%"=="" SET "DOWNLOAD_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
  IF NOT "%MVNW_REPOURL%"=="" SET "DOWNLOAD_URL=%MVNW_REPOURL%/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"

  IF NOT EXIST "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper" MKDIR "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper"

  WHERE curl >NUL 2>&1
  IF %ERRORLEVEL% EQU 0 (
    curl -fsSL "%DOWNLOAD_URL%" -o "%WRAPPER_JAR%"
  ) ELSE (
    WHERE wget >NUL 2>&1
    IF %ERRORLEVEL% EQU 0 (
      wget -q "%DOWNLOAD_URL%" -O "%WRAPPER_JAR%"
    )
  )

  REM Fallback to Java-based downloader if curl/wget unavailable or failed
  IF NOT EXIST "%WRAPPER_JAR%" (
    IF EXIST "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\MavenWrapperDownloader.java" (
      WHERE javac >NUL 2>&1
      IF %ERRORLEVEL% EQU 0 (
        javac -d "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper" "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\MavenWrapperDownloader.java" 1>NUL 2>&1
        java -cp "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper" MavenWrapperDownloader "%MAVEN_PROJECTBASEDIR%" 1>NUL 2>&1
      )
    )
  )

  IF NOT EXIST "%WRAPPER_JAR%" (
    ECHO Error: Maven Wrapper JAR could not be downloaded. Checked URL: %DOWNLOAD_URL%
    ECHO Install 'curl' or 'wget', or ensure Java/Javac are available for fallback.
    EXIT /B 1
  )
)

IF NOT "%JAVA_HOME%"=="" (
  SET "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
) ELSE (
  SET "JAVA_EXE=java"
)

"%JAVA_EXE%" %MAVEN_OPTS% -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" %WRAPPER_LAUNCHER% %*
endlocal
