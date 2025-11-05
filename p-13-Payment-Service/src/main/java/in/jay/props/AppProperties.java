package in.jay.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "razorpay")
public class AppProperties {
	
	private Map<String,String> keys = new HashMap<>();

	public Map<String, String> getkeys() {
		return keys;
	}

	public void setkeys(Map<String, String> keys) {
		this.keys = keys;
	}
	
	

}
