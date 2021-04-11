#!/bin/bash
flux bootstrap github \
  --components-extra=image-reflector-controller,image-automation-controller \
  --owner=HvAProjects \
  --repository=SuperSimpleSupplySystem \
  --branch=k8s \
  --path=./infra \
  --read-write-key=true