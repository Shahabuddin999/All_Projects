package zensar.BeanLifeCycle;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MusicChatApp {
	private Set<String> users;
	private List<String> message;
	private Map<String,String> chatRoom;
	private Properties prop;
	public Set<String> getUsers() {
		return users;
	}
	public void setUsers(Set<String> users) {
		this.users = users;
	}
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	public Map<String, String> getChatRoom() {
		return chatRoom;
	}
	public void setChatRoom(Map<String, String> chatRoom) {
		this.chatRoom = chatRoom;
	}
	
	public Properties getProp() {
		return prop;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	@Override
	public String toString() {
		return "MusicChatApp [users=" + users + ", message=" + message + ", chatRoom=" + chatRoom + ", prop=" + prop
				+ "]";
	}
	
}
