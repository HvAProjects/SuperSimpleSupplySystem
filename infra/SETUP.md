# Setting up for development pipeline

To set up this application you will need to have a running kubernetes cluster ready.

You can start a single node kubernetes cluster on an existing server using the following commands:
``` bash
k3sup install --ip $IP_ADDRESS
```

Please ensure the cluster has at least 2 VCPU and 4GB of memory

Other requirements:
https://github.com/alexellis/k3sup#pre-requisites-for-k3sup-servers-and-agents