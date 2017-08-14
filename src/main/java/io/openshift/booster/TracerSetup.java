package io.openshift.booster;

import com.uber.jaeger.samplers.ProbabilisticSampler;
import com.uber.jaeger.senders.HttpSender;
import io.opentracing.util.GlobalTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.opentracing.Tracer;

@Configuration
public class TracerSetup {

	@Bean
	public Tracer jaegerTracer() {
		
		String jaegerHttpUrl = System.getenv("JAEGER_HTTP_QUERY_URL");
		if (jaegerHttpUrl==null || jaegerHttpUrl.isEmpty()) {
			jaegerHttpUrl="http://localhost:14268/api/traces";
		}

		HttpSender httpTracer = new HttpSender(jaegerHttpUrl,0);
		return new com.uber.jaeger.Configuration("spring-boot",
				new com.uber.jaeger.Configuration.SamplerConfiguration(
						ProbabilisticSampler.TYPE, 1),
				new com.uber.jaeger.Configuration.ReporterConfiguration(httpTracer)).getTracer();
	}
}
