@ECHO OFF
if "%3"=="" (
    ECHO Illegal number of parameters
    ECHO "%0" {version} {mincraft directory} {output file}
    exit \b
)


start cmd /k Call ./build/install/AssetConverter/bin/AssetConverter.bat "%1" "%2" "%3"