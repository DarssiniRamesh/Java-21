@ECHO OFF
REM ----------------------------------------------------------------------------
REM Maven Wrapper Startup Script for Windows
REM ----------------------------------------------------------------------------

SETLOCAL
SET DIR=%~dp0
SET MAVEN_PROJECTBASEDIR=%DIR%

SET WRAPPER_DIR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper
SET WRAPPER_JAR=%WRAPPER_DIR%\maven-wrapper.jar
SET WRAPPER_PROPERTIES=%WRAPPER_DIR%\maven-wrapper.properties

IF NOT EXIST "%WRAPPER_JAR%" (
  FOR /F "usebackq tokens=1,2 delims==" %%A IN ("%WRAPPER_PROPERTIES%") DO (
    IF "%%A"=="wrapper.url" SET DOWNLOAD_URL=%%B
  )
  IF "%DOWNLOAD_URL%"=="" SET DOWNLOAD_URL=https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar
  IF NOT EXIST "%WRAPPER_DIR%" MKDIR "%WRAPPER_DIR%"
  WHERE curl >NUL 2>&1
  IF %ERRORLEVEL% EQU 0 (
    curl -fsSL "%DOWNLOAD_URL%" -o "%WRAPPER_JAR%"
  ) ELSE (
    WHERE wget >NUL 2>&1
    IF %ERRORLEVEL% EQU 0 (
      wget -q "%DOWNLOAD_URL%" -O "%WRAPPER_JAR%"
    ) ELSE (
      ECHO Error: Please install 'curl' or 'wget' to download Maven Wrapper.
      EXIT /B 1
    )
  )
)

SET WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

IF NOT "%JAVA_HOME%"=="" (
  SET JAVA_EXE=%JAVA_HOME%\bin\java.exe
) ELSE (
  SET JAVA_EXE=java
)

"%JAVA_EXE%" %MAVEN_OPTS% -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" %WRAPPER_LAUNCHER% %*
ENDLOCAL
