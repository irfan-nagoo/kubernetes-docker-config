# kubernetes-docker-config

![ingress](https://github.com/irfan-nagoo/kubernetes-docker-config/assets/96521607/b723ada4-0139-49d7-b223-02153d6d77b0)


This project is about Docker containerization and deployment of an application on Kubernetes cluster. A simple Java Microservice: raptor-service is used as an example as how to containerize and deploy any application (Java, DotNet, Angular etc.) on K8s cluster.


First things first, start with Docker and Kubernetes installation on local (Windows box). There are many ways to step up K8s cluster however, we can use following two popular ways of installation and setup:

1. Just install Docker from docker site and also enable Kubernetes on it. This is the simplest way to setup docker containerization runtime and test K8s single node cluster. It takes some time to install and setup all the required components. Once both Docker and K8s setup is completed, we should see both of them in green in Docker desktop.

2. Installing K8s from using kubeadm (This is for production installation). Any containerization runtime (Docker, Containerd, CRI-O etc.) which complies with Kubernetes CRI (Container Runtime Interface) could be used. For K8s with Docker installation, we need to install Docker runtime, cri-dockered, kubeadm, kubelet, kubectl. Refer to Kubernetes documentation for installation guide lines.

The "kubectl" is K8s client which is used to interact with K8s cluster. Once the K8s and Docker is setup on the local box, we need to install Kubernetes Dashboard as an additional component to manage K8s cluster through admin console. Follow these setup for Kubernetes Dashboard setup:

1. Run this command (run as admin preffered) to download and install K8s Dashboard:
		
			kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml
					
2. Run this command to setup admin user and role for the dashboard:
		
			kubectl apply -f C:\kubernetes-docker-config\k8s-config\k8s.cluster.binding.yaml
					
3. Run this command to check whether the K8s Dashboard is running:
					
			kubectl get pod -n kubernetes-dashboard
					
4. If its running (if not, wait for some time and check again), run this command to start proxy server:
		
			kubectl proxy
					
	K8s Dashboard login should be accessible at: http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login

5. Choose "Token" login and run this command to generate token:

			kubectl -n kubernetes-dashboard create token admin-user
					

Now you should be able to login and access K8s dashboard with different namespaces.
				
		
Deployment of application on Kubernetes cluster (With Nginx-Ingress):

1. Take "Dockerfile" file (.dockerignore file optional) from "docker-config" directory of this project and put it in "raptor-service" directory.

2. Open CLI in "raptor-service" directory and run this command to generate docker image of the raptor-service application:
				
            	docker build --tag raptor-service .
					
3. Run this command to check if the docker image is create:

			docker images

4. Once the docker image is created,  run this command to generate and deploy K8s Pods along with the required K8s service

			kubectl apply -f C:\kubernetes-docker-config\k8s-config\raptor-service-deployment.yaml

5. Check K8s console to make sure Pods (2 replicas) and service is created. Optionally, we can also run this command to check it:

		    	kubectl get pod -n default
					
    At this point, Pods are up and service is running however, we cannot access the service from outside the K8s cluster.
					
6. In order to access the service from outside, we need to install K8s Ingress. The Ingress requires an Ingress controller to handle outside traffic. We will use Nginx-Ingress as the default Ingress controller for this project. There are many other Ingress controllers available for K8s.
		
7. Run this command to install Nginx-Ingress ingress controller:
		
		    	kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.8.0/deploy/static/provider/cloud/deploy.yaml
			
8. Run this command to check Nginx-Ingress is running:

		    	kubectl get pod -n ingress-nginx
		
9. If its running (if not troubleshoot the issue), run this command to create the Ingress component for raptor-service:

		    	kubectl apply -f C:\Users\adin\Downloads\k8s-config\raptor-service-ingress.yaml
					

Try the Swagger url of the raptor-service now. Both http and https endpoints should be accessible and Swagger UI should come up:

http://localhost/swagger-ui.html  or https://localhost/swagger-ui.html
		
