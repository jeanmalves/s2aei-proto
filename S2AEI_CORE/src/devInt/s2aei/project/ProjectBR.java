package devInt.s2aei.project;

import java.util.Date;
import java.util.List;

import devInt.s2aei.student.Student;
import devInt.s2aei.util.BRException;
import devInt.s2aei.util.DAOFactory;
import devInt.s2aei.util.Logger;

public class ProjectBR {

	private ProjectDAO projectDAO;

	public ProjectBR() {
		this.projectDAO = DAOFactory.createProjectDAO();
	}

	public void save(Project project) throws BRException {

		Integer id = project.getIdProject();
		Date dateNow = new Date(System.currentTimeMillis());
		project.setLastModDate(dateNow);

		List<Project> projectUser = this.projectDAO
				.findProjectByStudent(project.getLeader());

		if (projectUser.size() >= 1) {
			Logger.log(
					Logger.PROJECT_BR,
					Logger.DBG,
					"Estudante j� � lider de outro projeto: "
							+ projectUser.size());
			throw new BRException("Estudante j� est� ativo em outro projeto.");
		}

		if (id == 0 || id == null) {
			project.setCreationDate(dateNow);
			project.setStatus("novo");

			this.projectDAO.save(project);

		} else
			this.projectDAO.update(project);

	}

	public void changeStatus(Project project, String status) throws BRException {
		project.setStatus(status);

		this.save(project);

	}

	public void delete(Project project) {
		this.projectDAO.delete(project);
	}

	public List<Project> listAll() {
		return this.projectDAO.listAll();
	}

	public Project findById(Integer idProject) {		
		return this.projectDAO.findById(idProject);
	}

}
