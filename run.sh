#!/usr/bin/env sh

#command -v packer >/dev/null 2>&1 || { echo >&2 "packer is required but not found"; exit 1; }

#echo "Building packer image"
#(cd ./vms/packer/; packer build "ubuntu.json")
#echo "Build of packer image finished"

(cd ./aldebaran-auth-micro/; ./gradlew bootRepackage)

mkdir -p ./vms/vagrant/dist/docker/aldebaran-auth
echo "Created aldebaran-auth directory"
cp ./aldebaran-auth-micro/aldebaran-auth-server/build/libs/aldebaran-auth-server-1.0-SNAPSHOT.jar ./vms/vagrant/dist/docker/aldebaran-auth
echo "Moved aldebaran-auth-server-1.0-SNAPSHOT.jar"
cp ./aldebaran-auth-micro/Dockerfile ./vms/vagrant/dist/docker/aldebaran-auth
echo "Moved aldebaran-auth Dockerfile"

(cd ./aldebaran-orders-micro/; ./gradlew bootRepackage)

echo "Created aldebaran-order directory"
mkdir -p ./vms/vagrant/dist/docker/aldebaran-order
cp ./aldebaran-orders-micro/aldebaran-order-server/build/libs/aldebaran-order-server-1.0-SNAPSHOT.jar ./vms/vagrant/dist/docker/aldebaran-order
echo "Moved aldebaran-order-server-1.0-SNAPSHOT.jar"
cp ./aldebaran-orders-micro/Dockerfile ./vms/vagrant/dist/docker/aldebaran-order
echo "Moved aldebaran-order Dockerfile"
