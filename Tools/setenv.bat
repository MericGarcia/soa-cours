set "CATALINA_HOME=%cd%"
if exist "%CATALINA_HOME%\catalina.bat" set "CATALINA_HOME=%cd%\.."
set "JAVA_HOME=%CATALINA_HOME%\jdk7-win"
