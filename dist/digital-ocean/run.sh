#!/usr/bin/env sh

(cd ../../aldebaran-auth-micro/; ./gradlew bootRepackage)

cp ../../aldebaran-auth-micro/aldebaran-auth-server/build/libs/aldebaran-auth-server-1.0-SNAPSHOT.jar ./aldebaran-auth
echo "Moved aldebaran-auth-server-1.0-SNAPSHOT.jar"
cp ../../aldebaran-auth-micro/Dockerfile ./aldebaran-auth
echo "Moved aldebaran-auth Dockerfile"

(cd ../../aldebaran-orders-micro/; ./gradlew bootRepackage)

cp ../../aldebaran-orders-micro/aldebaran-order-server/build/libs/aldebaran-order-server-1.0-SNAPSHOT.jar ./aldebaran-order
echo "Moved aldebaran-order-server-1.0-SNAPSHOT.jar"
cp ../../aldebaran-orders-micro/Dockerfile ./aldebaran-order
echo "Moved aldebaran-order Dockerfile"
