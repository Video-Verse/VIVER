package com.project.viver.service.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.viver.common.constraint.Optional;
import com.project.viver.entity.common.BaseEntity;

import jakarta.transaction.Transactional;

abstract public class BaseService<T extends BaseEntity, ID extends Serializable, Repo extends JpaRepository<T, ID>>
		extends CrudService<T, ID, Repo> {

	public BaseService(Repo repository) {
		super(repository);
	}

	public T get(ID id) {
		T e = repository.findById(id).orElse(null);
		return e;
	}

	@Transactional
	public T insert(T t ) {
		t.setCrtDt(LocalDateTime.parse(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		t.setMdfnDt(LocalDateTime.parse(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		t.setDelYn(Optional.No.value());
		return super.insert(t);
	}

	@Transactional
	public T update(ID id, T e) {
		e.setMdfnDt(LocalDateTime.parse(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return super.update(id, e);
	}

	@Transactional
	public void delete(ID id) {
		T e = repository.findById(id).orElse(null);
		e.setDelYn(Optional.Yes.value());
		this.update(id, e);
	}

	@Transactional
	public void undelete(ID id) {
		T e = repository.findById(id).orElse(null);
		e.setDelYn(Optional.No.value());
		this.update(id, e);
	}

	public Page<T> search(Map<String, String> params, PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}
}
