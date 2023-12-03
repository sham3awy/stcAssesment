package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item extends Space{

	private String type;

	@ManyToOne
	@JoinColumn(name = "permission_group_id")
	private PermissionGroup permissionGroup;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PermissionGroup getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}
}
