package com.wdkim.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTests {
	@Setter @Autowired
	private DataSource dataSource;
	@Setter @Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testExist() {
		assertNotNull(dataSource);
	}
	
	@Test
	public void testConnection() { // 히카리
		try(Connection conn = dataSource.getConnection()) {
			log.info(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConnection2() { // 마이바티스 테스트
		try(
				SqlSession SessionFactory = sqlSessionFactory.openSession();
				Connection conn = dataSource.getConnection()
			) {
			log.info(SessionFactory);
			log.info(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
