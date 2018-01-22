#!/bin/bash

sudo mkdir -p /var/mysql/data
sudo chown -R vagrant /var/mysql/data

sudo mkdir -p /var/mysql/data
sudo chown -R vagrant /var/mysql/data

# docker socket without sudo
sudo usermod -a -G docker vagrant

# elastich search map count
sudo sysctl -w vm.max_map_count=262144