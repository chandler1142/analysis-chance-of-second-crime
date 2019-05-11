package chanseofsecondcrime.controller

import chanseofsecondcrime.service.MainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class MainController(@Autowired val service: MainService) {

  @GetMapping(path = Array("/result"))
  def result: String = service.getResult.toString
}