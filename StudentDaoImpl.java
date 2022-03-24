package com.spring.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.spring.app.pojo.Student;

@Component
public class StudentDaoImpl implements StudentDao {

	private JdbcTemplate jdbcTemplate;

	public StudentDaoImpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}

	public int create(Student student) {

		String sql = "insert into student(stu_name,stu_email,stu_course) values(?,?,?)";

		try {

			int counter = jdbcTemplate.update(sql,
					new Object[] { student.getName(), student.getEmail(), student.getCourse() });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<Student> read() {
		List<Student> studentList = jdbcTemplate.query("SELECT * FROM STUDENT", new RowMapper<Student>() {

			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();

				student.setId(rs.getInt("stu_id"));
				student.setName(rs.getString("stu_name"));
				student.setEmail(rs.getString("stu_email"));
				student.setCourse(rs.getString("stu_course"));

				return student;
			}

		});

		return studentList;
	}

	public List<Student> findStudentById(int studentId) {

		List<Student> studentList = jdbcTemplate.query("SELECT * FROM STUDENT where stu_id=?",
				new Object[] { studentId }, new RowMapper<Student>() {

					public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
						Student student = new Student();

						student.setId(rs.getInt("stu_id"));
						student.setName(rs.getString("stu_name"));
						student.setEmail(rs.getString("stu_email"));
						student.setCourse(rs.getString("stu_course"));

						return student;
					}

				});

		return studentList;
	}

	public int update(Student student) {
		String sql = "update  student set stu_name=?, stu_email=?, stu_course=? where stu_id=?";

		try {

			int counter = jdbcTemplate.update(sql,
					new Object[] { student.getName(), student.getEmail(), student.getCourse(), student.getId() });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int delete(int studentId) {
		String sql = "delete from student where stu_id=?";

		try {

			int counter = jdbcTemplate.update(sql, new Object[] { studentId });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

}
