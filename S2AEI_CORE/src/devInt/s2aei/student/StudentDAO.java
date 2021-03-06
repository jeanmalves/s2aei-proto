package devInt.s2aei.student;

import java.util.List;

public interface StudentDAO {
	public void save(Student student);
	public void update(Student student);
	public void delete(Student student);
	public List<Student> listAll();
	
	// new changes v2
	public Student findById(Integer idStudent);
	public Student findStudentByLogin(String login);
}
