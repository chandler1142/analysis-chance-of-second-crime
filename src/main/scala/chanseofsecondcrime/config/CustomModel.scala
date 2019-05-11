package chanseofsecondcrime.config

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.{LogisticRegressionModel, LogisticRegressionWithLBFGS}
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean

@Bean
class CustomModel(@Autowired val sc: SparkContext) {

  private val FILES_LOCATION: String = "src/main/resources/static/csv/"
  private val CSV_FORMAT_ALL: String = "*.csv"

  var modelInstance: LogisticRegressionModel = {
    val data = sc.textFile(FILES_LOCATION.concat(CSV_FORMAT_ALL))
    val parsedData = data.mapPartitionsWithIndex((index, iter) => if (index == 0) iter.drop(1) else iter).map(line => {
      val parts = line.split(",")
      LabeledPoint(parts(6).toDouble, Vectors.dense(parts.slice(3, 6).map(_.toDouble)))
    })

    val splitsData = parsedData.randomSplit(Array(0.8, 0.2))
    val trainingData = splitsData(0)
    val testData = splitsData(1)

    val model = new LogisticRegressionWithLBFGS().setNumClasses(2).run(trainingData)

    val predictionAndLabes = testData.map((x: LabeledPoint) => {
      val prediction = model.predict(x.features)
      (x.label, prediction)
    })

    val metrics = new MulticlassMetrics(predictionAndLabes)
    val accuracy = metrics.accuracy

    model
  }

  def analyze(array: Array[Double]): Double = {
    modelInstance.clearThreshold()
    modelInstance.predict(Vectors.dense(array))
  }
}
