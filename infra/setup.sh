#!/bin/bash
kubectl apply -f bootstrap
fluxctl identity --k8s-fwd-ns flux