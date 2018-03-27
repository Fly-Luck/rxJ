package rxj;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.management.MalformedObjectNameException;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadPoolConfig {

	@Bean
	NamedExecutorService MainExecutorService(ThreadPoolProperties threadPoolProperties) throws MalformedObjectNameException {
		return new NamedExecutorService("mainPool", threadPoolProperties.coreSize, threadPoolProperties.maxSize, 
				threadPoolProperties.keepAlive, TimeUnit.SECONDS, threadPoolProperties.queueSize, new ThreadPoolExecutor.DiscardPolicy());
	}
}
