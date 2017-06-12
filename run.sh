#!/usr/bin/env sh

(cd ./aldebaran-auth-micros/; ./gradlew bootRepackage)

mkdir -p ./vms/vagrant/docker/aldebaran-auth
echo "Created aldebaran-auth directory"
cp ./aldebaran-auth-micros/aldebaran-auth-server/build/libs/aldebaran-auth-server-1.0-SNAPSHOT.jar ./vms/vagrant/docker/aldebaran-auth
echo "Moved aldebaran-auth-server-1.0-SNAPSHOT.jar"
cp ./aldebaran-auth-micros/Dockerfile ./vms/vagrant/docker/aldebaran-auth
echo "Moved aldebaran-auth Dockerfile"

(cd ./aldebaran-orders-micro/; ./gradlew bootRepackage)

echo "Created aldebaran-order directory"
mkdir -p ./vms/vagrant/docker/aldebaran-order
cp ./aldebaran-orders-micro/aldebaran-order-server/build/libs/aldebaran-order-server-1.0-SNAPSHOT.jar ./vms/vagrant/docker/aldebaran-order
echo "Moved aldebaran-order-server-1.0-SNAPSHOT.jar"
cp ./aldebaran-orders-micro/Dockerfile ./vms/vagrant/docker/aldebaran-order
echo "Moved aldebaran-order Dockerfile"
