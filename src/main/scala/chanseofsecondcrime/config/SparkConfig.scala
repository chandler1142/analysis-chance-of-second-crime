package chanseofsecondcrime.config

import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
class SparkConfig {
  @Value("${spark.app.name}") private val appName: String = null
  @Value("${spark.master}") private val masterUri: String = null

  @Bean def conf: SparkConf = new SparkConf().setAppName(appName).setMaster(masterUri)

  @Bean def sc = new SparkContext(conf)

  @Bean def model = new CustomModel(sc)
}