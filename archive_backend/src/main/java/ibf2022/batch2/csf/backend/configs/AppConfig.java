package ibf2022.batch2.csf.backend.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AppConfig {
    @Value("${ACCESS_KEY}")
    private String accessKey;

    @Value("${SECRET_KEY}")
    private String secretKey;

    @Bean
    public AmazonS3 createS3Client(){

    //Create the credentials with access and secret key
    BasicAWSCredentials cred = new BasicAWSCredentials(accessKey, secretKey);

    //Config the endpoint (HTTP access) to our bucket
    EndpointConfiguration epConfig = new EndpointConfiguration("sgp1.digitaloceanspaces.com", "sgp1");

    AmazonS3 client = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(epConfig)
            .withCredentials(new AWSStaticCredentialsProvider(cred))
            .build();
    return client;
}


}
