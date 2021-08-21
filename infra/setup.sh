#!/bin/bash
kubectl apply -f flux-system/gotk-components.yaml
kubectl apply -f flux-system/gotk-sync.yaml
