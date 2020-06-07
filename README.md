# Asset converter
Minecraft asset files store in their hash and this make asset files to lose its own name and folder structure.
This simple project restore asset file folder structure and name.

# Installation
JDK (>=8) is required to compile and execute.

Run following command to build project.

### Linux/Mac
```
./gradlew installDist
```
### Windows
```
./gradlew.bat installDist
```

# Execution
Execute following command to run program

### Linux/Mac
```
./run.sh {version} {minecraft directory} {output}
```
### Windows
```
./run.bat {version} {minecraft directory} {output}
```

Version is version number of minecraft without patch version.

Minecraft directory is directory where minecraft is installed, which is `%appdata%/.minecraft` in windows or `~/Library/Application Support/minecraft` in mac.

Output is path to save result. If path is ends with .zip, result save as zip file. Otherwise, save to directory.