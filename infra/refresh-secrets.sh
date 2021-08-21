#!/bin/bash

# navigate to directory of the script
cd "$(dirname "$(readlink -f "$0")")"

kubectl create secret generic ssss-secrets-properties \
--namespace ssss \
--from-file=../backend/src/main/resources/secrets/secrets.properties \
--dry-run=client \
-o yaml > secrets.yaml

kubeseal --format=yaml \
< secrets.yaml > ssss/secrets.yaml

rm -Rf secrets.yaml

cd ..
git reset HEAD -- .
git add "infra/ssss/secrets.yaml"
git commit -m "update secrets.yaml"
