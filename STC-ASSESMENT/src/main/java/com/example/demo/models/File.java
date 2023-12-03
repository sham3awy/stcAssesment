package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class File extends Item{
	// Binary data storage (You can use appropriate data types like byte[])
	private byte[] binary;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	public byte[] getBinary() {
		return binary;
	}

	public void setBinary(byte[] binary) {
		this.binary = binary;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
