package io.gatling.http.request.builder

import com.ning.http.client.Request
import io.gatling.core.session.{ Expression, Session }
import io.gatling.http.config.HttpProtocol
import io.gatling.http.action.ws.OpenWebSocketActionBuilder
import io.gatling.http.request.builder.ahc.AHCWebSocketRequestBuilder

object OpenWebSocketRequestBuilder {

	implicit def toActionBuilder(requestBuilder: OpenWebSocketRequestBuilder) = new OpenWebSocketActionBuilder(requestBuilder.commonAttributes.requestName, requestBuilder.wsName, requestBuilder)
}

class OpenWebSocketRequestBuilder(commonAttributes: CommonAttributes, val wsName: String) extends RequestBuilder[OpenWebSocketRequestBuilder](commonAttributes) {

	private[http] def newInstance(commonAttributes: CommonAttributes) = new OpenWebSocketRequestBuilder(commonAttributes, wsName)

	def build(protocol: HttpProtocol): Expression[Request] =
		(session: Session) => new AHCWebSocketRequestBuilder(commonAttributes, session, protocol).ahcRequest
}
