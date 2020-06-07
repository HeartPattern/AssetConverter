if [ "$#" -ne 3 ]; then
  echo "Illegal number of parameters"
  echo "$0 {version} {minecraft directory} {output file}"
  exit
fi

app="./build/install/AssetConverter/bin/AssetConverter"
version="$1"
mc_dir="$2"
result="$3"

eval "./gradlew installDist"
eval "$app $version $mc_dir $result"