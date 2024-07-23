package com.boroousseni.gestionstock.models;

import java.time.Instant;
import java.io.Serializable;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreatedDate
	@Column(name = "creationDate", nullable = false, updatable = false)
	private Instant creationDate;

	@LastModifiedDate
	@Column(name = "lastModifiedDate")
	private Instant lastModifiedDate;

}
