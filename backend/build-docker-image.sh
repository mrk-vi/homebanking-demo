#!/bin/bash

../gradlew :backend:bootJar && docker build -t uni.mirkoz.homebanking-demo/backend .