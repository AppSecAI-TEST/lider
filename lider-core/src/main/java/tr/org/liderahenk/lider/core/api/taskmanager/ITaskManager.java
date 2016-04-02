package tr.org.liderahenk.lider.core.api.taskmanager;

import java.util.List;

import tr.org.liderahenk.lider.core.api.rest.requests.IRequest;
import tr.org.liderahenk.lider.core.api.rest.requests.ITaskCommandRequest;
import tr.org.liderahenk.lider.core.model.ldap.LdapEntry;

/**
 * Provides {@link ITask} lifecycle management services
 * 
 * 
 * @author <a href="mailto:birkan.duman@gmail.com">Birkan Duman</a>
 *
 */
public interface ITaskManager {

	/**
	 * creates a task for request
	 * 
	 * @param entries
	 * 
	 * @param {@link
	 * 			IRequest} to be submitted as a task
	 * @return String[] of tasks created as a result of {@link IRequest}, will
	 *         be a single task for single node task, or all subtask id's in
	 *         case of a subtree request creating subtasks
	 * @throws TaskSubmissionFailedException
	 *             on any failure during task creation
	 * 
	 */
	void addTask(ITaskCommandRequest request, List<LdapEntry> entries) throws TaskSubmissionFailedException;

}
