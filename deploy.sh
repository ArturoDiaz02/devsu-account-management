#!/bin/bash

# Crear red pública (bridge)
docker network create \
  --driver bridge \
  --opt com.docker.network.bridge.name=public \
  public

# Crear red privada (bridge)
docker network create \
  --driver bridge \
  --opt com.docker.network.bridge.name=private \
  private

# Deploy discovery
docker-compose -p discovery -f compose/discovery.yml up -d

# Deploy databases
docker-compose -p storage -f compose/storage.yml up -d

# Deploy broker
docker-compose -p broker -f compose/broker.yml up -d

sleep 3

# Deploy backend
docker-compose -p backend -f compose/backend.yml up -d --scale sv-customer=2 --scale sv-account=2