# Setting upon kubernetes

## prerequisites

- Ensure you have a kubernetes cluster running (for lical development you can use the docker built in or [microk8s](https://microk8s.io/))
- Ensure you have [kubectl installed and setup](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
- Ensure you have [flux CLI](https://toolkit.fluxcd.io/get-started/) installed.

## SuperSimpleSupplySystem Setup

1. Run `./setup.sh`
2. Congratulations SSSS is being installed in the cluster and will automatically be kept up to date.

## Updating the secrets.properties

1. Ensure secrets.properties is up to date on your local machine
2. Ensure [kubeseal](https://github.com/bitnami-labs/sealed-secrets/releases/latest) is installed
3. Navigate to the `infra` folder
4. Generate the unencrypted secret using:

    ``` bash
    kubectl create secret generic ssss-secrets-properties \
    --namespace ssss \
    --from-file=../backend/src/main/resources/secrets.properties \
    --dry-run=client \
    -o yaml > secrets.yaml
    ```

5. Fetch the sealing secret using

    ``` bash
        kubeseal --fetch-cert \
        --controller-name=sealed-secrets \
        --controller-namespace=flux-system \
        > pub-sealed-secrets.pem
    ```

6. Seal the secret using:

    ``` bash
    kubeseal --format=yaml --cert=pub-sealed-secrets.pem \
    < secrets.yaml > ssss/secrets.yaml
    ```

7. Remove all unencryped files using:

    ``` bash
    rm -Rf secrets.yaml
    rm -Rf pub-sealed-secrets.pem
    ```

8. Commit changes to the repo

Alternatively you can use the provided convenience script using: `./refresh-secrets.sh`

## Making changes

The Flux daemon will automatically watch the container images and the infra folder for changes. If it detects a change it will try to make the cluster state match the state in git. This means that to change the settings in the cluste, all you have to do is make a change in the infra folder OR push a new version of the container images. Flux will detect this and update the repo and/or cluster accordingly.
