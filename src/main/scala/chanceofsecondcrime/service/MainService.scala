package chanceofsecondcrime.service


import chanceofsecondcrime.config.CustomModel
import chanceofsecondcrime.entity.ResponseEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MainService(@Autowired val model: CustomModel) {

  def getResult(mother: Boolean, father: Boolean, severity: Int) = ResponseEntity(model.analyze(Array(bool2int(mother), bool2int(father), severity).map(_.toDouble)))

  def bool2int(param: Boolean): Int = if (param) 1 else 0
}
