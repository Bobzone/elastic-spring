package com.bobzone.elasticspring.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

//@Configuration
@EnableElasticsearchRepositories(basePackages = "com.bobzone.elasticspring.repository")
public class ElasticsearchConfig {

    @Value("localhost")
    private String host;

    @Value("9300")
    private int port;

    @Value("cars-cluster")
    private String clusterName;

    @Bean
    public Client client() throws UnknownHostException {

        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", clusterName)
                .build();

        return new TransportClient.Builder()
                .settings(settings)
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }
}

