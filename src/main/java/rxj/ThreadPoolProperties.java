package rxj;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pool")
public class ThreadPoolProperties {
	int coreSize = 1;
	int maxSize = 10;
	int keepAlive = 1200;
	int queueSize = -1;
}
