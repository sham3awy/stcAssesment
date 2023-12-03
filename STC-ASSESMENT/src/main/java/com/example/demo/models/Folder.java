package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Folder extends Space {
	// Define ManyToOne relationship with Space
	@ManyToOne
	@JoinColumn(name = "space_id")
	private Space space;

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}
}