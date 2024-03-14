package com.estsoft.blogjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estsoft.blogjpa.model.Article;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {

	// List<Article> findByTitle(String title); -> SELECT
	//
	// void DeleteByContent(String content);   -> DELETE
	// save  -> INSERT
	// JPQL = Java Persistence Query Language
	@Modifying
	@Query("update Article set title = :title where id = :id")
	void updateTitle(Long id, String title);
}
