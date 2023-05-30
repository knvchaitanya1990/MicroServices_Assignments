package com.example.vehicleServiceStation.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceInstanceInfo {

    @Autowired
    DiscoveryClient discoveryClient;

    public String getInstanceUrl(String serviceName) {
        // Retrieve the list of service instances by serviceId
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances.isEmpty()) {
            throw new IllegalStateException("No instances available for the service");
        }
        // Get the first instance (assuming there's only one instance running)
        ServiceInstance instance = instances.get(0);

        // Get the host and port information
        String host = instance.getHost();
        int port = instance.getPort();

        // Construct the actual running instance URL
        return "http://" + host + ":" + port;
    }
}
