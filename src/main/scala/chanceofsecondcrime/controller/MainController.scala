package chanceofsecondcrime.controller

import chanceofsecondcrime.service.MainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

@RestController
class MainController(@Autowired val service: MainService) {

  @GetMapping(path = Array("/result"), produces = Array("application/json"))
  def result(@RequestParam hasMother: Boolean, @RequestParam hasFather: Boolean, @RequestParam firstCrimeSeverity: Int) = service.getResult(hasMother, hasFather, firstCrimeSeverity)

}