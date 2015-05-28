package edu.zipcloud.cloudstreetmarket.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import edu.zipcloud.cloudstreetmarket.core.enums.Role;

@Entity
@Table(name="authorities", uniqueConstraints={@UniqueConstraint(columnNames = {"userid" , "authority"})})
public class Authority extends AbstractAutogeneratedId<Long> implements GrantedAuthority{

	private static final long serialVersionUID = 1990856213905768044L;

	public Authority() {
	}
	
	public Authority(User user, Role authority) {
		this.user = user;
		this.authority = authority;
	}

	public Authority(String string) {
		this.authority = Role.valueOf(string);
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable=false)
	private User user;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role authority;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority.toString();
	}

	public void setAuthority(Role authority) {
		this.authority = authority;
	}
}