package com.growingitskill.mapper;

import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.growingitskill.config.MybatisJndiConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class CategoryMapperTest {

	private static final Logger logger = LoggerFactory.getLogger(CategoryMapperTest.class);

	@BeforeClass
	public static void jndiBind() throws NamingException {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/test");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		builder.bind("jdbc/test", dataSource);
	}

	@Autowired
	private CategoryMapper categoryMapper;

	/*@Test
	public void selectCategoryById() throws Exception {
		long id = 2;

		CategoryVO categoryVO = categoryMapper.readCategoryById(id);

		logger.info(categoryVO.toString());
	}*/

	/*@Test
	public void selectCategory() throws Exception {
		List<CategoryVO> lists = categoryMapper.listAll();

		for (CategoryVO categoryVO : lists) {
			logger.info(categoryVO.toString());
		}
	}*/
	
	/*@Test
	public void insertCategory() throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setTerm("카테고리다");
		categoryVO.setSlugTerm("slug다");
		categoryVO.setParent(2);
		
		categoryMapper.create(categoryVO);
		
		logger.info("id: " + categoryVO.getId());
	}*/
	
	/*@Test
	public void updateTerm() throws Exception {
		long id = 3;
		String term = "마자용";
		
		categoryMapper.updateTermById(id, term);
	}*/
	
	/*@Test
	public void updateSlugTerm() throws Exception {
		long id = 5;
		String slugTerm = "fnfnfn";
		
		categoryMapper.updateSlugTermById(id, slugTerm);
	}*/
	
	/*@Test
	public void updateParent() throws Exception {
		long id = 5;
		long parent = 4;
		
		categoryMapper.updateParentById(id, parent);
	}*/
	
	@Test
	public void deleteCategory() throws Exception {
		long id = 4;
		
		categoryMapper.deleteById(id);
	}

}