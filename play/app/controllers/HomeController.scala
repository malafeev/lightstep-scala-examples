package controllers

import javax.inject._

import io.opentracing.tag.Tags
import play.api.mvc._
import utils.TracerUtils

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    val span = TracerUtils.tracer.buildSpan(request.method)
        .withTag(Tags.HTTP_METHOD.getKey, request.method)
        .withTag(Tags.HTTP_URL.getKey, request.uri)
        .startActive()
    try {
      Ok(views.html.index())
    } finally {
      span.close()
    }
  }
}
