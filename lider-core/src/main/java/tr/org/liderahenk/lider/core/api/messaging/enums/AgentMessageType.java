package tr.org.liderahenk.lider.core.api.messaging.enums;

/**
 * Types used when sending messages <b>from agents to Lider</b>.<br/>
 * <br/>
 * 
 * <b>TASK_STATUS</b>: Contains task-related messages.<br/>
 * <b>REGISTER</b>: Indicates that sender (agent) wants to register to the
 * system.<br/>
 * <b>REGISTER_LDAP</b>: Indicates that sender (agent) wants to register to the
 * ldap.<br/>
 * <b>UNREGISTER</b>: Indicates that sender (agent) wants to unregister from the
 * system.<br/>
 * <b>GET_POLICIES</b>: Agent sends this message during user login.<br/>
 * <b>LOGIN</b>: Agent sends this message for log purposes during user login.
 * <br/>
 * <b>LOGOUT</b>: Agent sends this message for log purposes during user logout.
 * <br/>
 * 
 * @author <a href="mailto:birkan.duman@gmail.com">Birkan Duman</a>
 * @author <a href="mailto:emre.akkaya@agem.com.tr">Emre Akkaya</a>
 * @author <a href="mailto:bm.volkansahin@gmail.com">Volkan Şahin</a>
 * 
 */
public enum AgentMessageType {
	TASK_STATUS, REGISTER, UNREGISTER, REGISTER_LDAP, GET_POLICIES, LOGIN, LOGOUT
}
