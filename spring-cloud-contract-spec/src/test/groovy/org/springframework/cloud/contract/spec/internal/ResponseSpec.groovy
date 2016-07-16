package org.springframework.cloud.contract.spec.internal

import spock.lang.Specification
/**
 * @author Marcin Grzejszczak
 */
class ResponseSpec extends Specification {

	def 'should throw exception when on response side a value contains regex for client'() {
		given:
			Response response = new Response()
		when:
			response.with {
				value(server("foo"), client(regex("foo")))
			}
		then:
			thrown(IllegalStateException)
		when:
			response.with {
				value(client(regex("foo")), server("foo"))
			}
		then:
			thrown(IllegalStateException)
	}

	def 'should generate a value if only regex is passed for server'() {
		given:
			Response request = new Response()
			DslProperty property
		when:
			request.with {
				property = value(server(regex("[0-9]{5}")))
			}
		then:
			(property.serverValue as String).matches(/[0-9]{5}/)
	}
}