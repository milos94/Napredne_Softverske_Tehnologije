package com.milos.kindergarden.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.milos.kindergarden.models.Guardian;

public class GuardianUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	Guardian guardian;

	public GuardianUserDetails(Guardian guardian) {
		super();
		this.guardian = guardian;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority("ROLE_GUARDIAN"));

        return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return guardian.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return guardian.getFirstName()+'.'+guardian.getLastName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Guardian getUser() {
		return guardian;
	}

}
