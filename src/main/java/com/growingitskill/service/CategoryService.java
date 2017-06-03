package com.growingitskill.service;

import java.util.List;

import com.growingitskill.domain.CategoryVO;

public interface CategoryService {
	
	List<CategoryVO> listAll() throws Exception;
	
	void addCategory(CategoryVO categoryVO) throws Exception;
	
	void renameCategoryById(long id, String term) throws Exception;
	
	void modifyCategorySlugTermById(long id, String slugTerm) throws Exception;
	
	void moveCategory(long id, long parent) throws  Exception;
	
	void removeCategory(long id) throws Exception;

}
