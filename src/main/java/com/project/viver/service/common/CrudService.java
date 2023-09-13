package com.project.viver.service.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

abstract public class CrudService<T, ID extends Serializable, Repo extends JpaRepository<T, ID>> {

	protected Repo repository;

	public CrudService(Repo repository) {
		this.repository = repository;
	}

	public T get(ID id) {
		T e = repository.findById(id).orElse(null);
		return e;
	}

	@Transactional
	public T insert(T t) {
		return repository.save(t);
	}

	@Transactional
	public T update(ID id, T e) {
		return repository.save(e);
	}

	@Transactional
	public void deleteForce(ID id) {
		T o = repository.findById(id).orElse(null);
		repository.delete(o);
	}

//	public Page<T> search(Map<String, String> params, Authentication authentication, PageRequest pageRequest) {
//		return repository.findAll(pageRequest);
//	}
}
