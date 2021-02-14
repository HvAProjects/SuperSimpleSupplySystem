# Setting upon kubernetes

## prerequisites

- Ensure you have a kubernetes cluster running (for lical development you can use the docker built in or [microk8s](https://microk8s.io/))
- Ensure you have [kubectl installed and setup](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
- Ensure you have [fluxctl](https://docs.fluxcd.io/en/1.21.1/references/fluxctl/) installed.

## SuperSimpleSupplySystem Setup

1. Run `./setup.sh`
2. Use the SSH public key from the script output to create a [deploy key in github](https://github.com/HvAProjects/SuperSimpleSupplySystem/settings/keys)
3. Congratulations SSSS is being installed in the cluster and will automatically be kept up to date.

## Making changes

The Flux daemon will automatically watch the container images and the infra folder for changes. If it detects a change it will try to make the kuster state match the state in git. This means that to change the settings in the cluste, all you have to do is make a change in the infra folder OR push a new version of the container images. Flux will detect this and update the repo and/or cluster accordingly.
