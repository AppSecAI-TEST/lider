package tr.org.liderahenk.lider.messaging.responses;

import java.io.Serializable;

import org.osgi.service.blueprint.reflect.RegistrationListener;

import tr.org.liderahenk.lider.core.api.messaging.enums.RegistrationMessageStatus;
import tr.org.liderahenk.lider.core.api.messaging.responses.IRegistrationMessageResponse;

/**
 * Registration information returned from {@link RegistrationListener}.
 * 
 * @author <a href="mailto:emre.akkaya@agem.com.tr">Emre Akkaya</a>
 *
 */
public class RegistrationMessageResponseImpl implements IRegistrationMessageResponse, Serializable {

	private static final long serialVersionUID = 7047749308792675311L;

	private RegistrationMessageStatus status;

	private String message;

	String agentDn;

	public RegistrationMessageResponseImpl() {
		super();
	}

	public RegistrationMessageResponseImpl(RegistrationMessageStatus status, String message, String agentDn) {
		super();
		this.status = status;
		this.message = message;
		this.agentDn = agentDn;
	}

	public RegistrationMessageStatus getStatus() {
		return status;
	}

	public void setStatus(RegistrationMessageStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAgentDn() {
		return agentDn;
	}

	public void setAgentDn(String agentDn) {
		this.agentDn = agentDn;
	}

}