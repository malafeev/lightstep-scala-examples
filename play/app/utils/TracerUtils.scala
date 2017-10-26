package utils

import com.lightstep.tracer.jre.JRETracer
import com.lightstep.tracer.shared.Options.OptionsBuilder

object TracerUtils {
  val tracer = new JRETracer(new OptionsBuilder()
      .withComponentName("scala-play")
      .withAccessToken("replace me !!!")
      .build())

}
