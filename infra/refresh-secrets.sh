#!/bin/bash

# navigate to directory of the script
cd "$(dirname "$(readlink -f "$0")")"

kubectl create secret generic ssss-secrets-properties \
--namespace ssss \
--from-file=../backend/src/main/resources/secrets.properties \
--dry-run=client \
-o yaml > secrets.yaml

kubeseal --fetch-cert \
--controller-name=sealed-secrets \
--controller-namespace=flux-system \
> pub-sealed-secrets.pem

kubeseal --format=yaml --cert=pub-sealed-secrets.pem \
< secrets.yaml > ssss/secrets.yaml

rm -Rf secrets.yaml
rm -Rf pub-sealed-secrets.pem

cd ..
git reset HEAD -- .
git add "infra/ssss/secrets.yaml"
git commit -m "update secrets.yaml"
