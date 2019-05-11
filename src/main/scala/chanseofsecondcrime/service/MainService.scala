package chanseofsecondcrime.service


import chanseofsecondcrime.config.CustomModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MainService(@Autowired val model: CustomModel) {

  def getResult: Double = {
    model.analyze(Array(1,1,1).map(_.toDouble))
  }

}
