package tr.org.liderahenk.lider.core.api.messaging;

import java.util.List;

/**
 * Provides messaging services
 * 
 * @author <a href="mailto:birkan.duman@gmail.com">Birkan Duman</a>
 *
 */
public interface IMessagingService {

	void init();

	/**
	 * 
	 * @param jid of message sender
	 * @param message text message
	 * @throws Exception
	 */
	void messageReceived(String jid, String message) throws Exception;

	/**
	 * 
	 * @param to recipient of message
	 * @param message to be sent
	 * @throws Exception
	 */
	void sendMessage(String to, String message) throws Exception;

	/**
	 * 
	 * @param message {@link IMessage} to be sent
	 * @throws Exception
	 */
	void sendMessage(IMessage message) throws Exception;

	/**
	 * 
	 * @param jid
	 * @return true if jid is online, false otherwise
	 */
	boolean isRecipientOnline(String jid);

	/**
	 * @deprecated no use 
	 * @param message {@link IMessage} to be sent
	 * @param resource specific xmpp resource of recipient
	 * @throws Exception
	 */
	void sendMessage(IMessage message, String resource) throws Exception;
	
	
	List<String> getOnlineUsers(); 

}