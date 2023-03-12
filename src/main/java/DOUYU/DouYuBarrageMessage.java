package DOUYU;

import java.util.Objects;

public class DouYuBarrageMessage {
	private String idOfLi;
	private String nickname;
	private String message;

	public DouYuBarrageMessage() {
		super();
	}

	public DouYuBarrageMessage(String idOfLi, String nickname, String message) {
		super();
		this.idOfLi = idOfLi;
		this.nickname = nickname;
		this.message = message;
	}

	public String getIdOfLi() {
		return idOfLi;
	}

	public void setIdOfLi(String idOfLi) {
		this.idOfLi = idOfLi;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOfLi, message, nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DouYuBarrageMessage other = (DouYuBarrageMessage) obj;
		return Objects.equals(idOfLi, other.idOfLi) && Objects.equals(message, other.message)
				&& Objects.equals(nickname, other.nickname);
	}

	@Override
	public String toString() {
		return "DouYuBarrageMessage [idOfLi=" + idOfLi + ", nickname=" + nickname + ", message=" + message + "]";
	}

}
