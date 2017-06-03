#!/usr/bin/env bash

sudo apt-get update

sudo apt-get install -y --no-install-recommends \
    linux-image-extra-$(uname -r) \
    linux-image-extra-virtual
	
sudo apt-get install -y --no-install-recommends \
    apt-transport-https \
    ca-certificates \
    software-properties-common \
    curl
	
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo add-apt-repository \
       "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
       $(lsb_release -cs) \
       stable"
	   
sudo apt-get update 

sudo apt-get -y install docker-ce